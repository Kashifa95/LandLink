import React, { useEffect, useRef, useState } from 'react'
import { get } from '../api/ApiService'
import { Plot } from '../types'

export default function LayoutViewer({
  layoutId,
  svgFilePath,
  onClose
}: {
  layoutId: string
  svgFilePath: string
  onClose: () => void
}) {
  const [svgText, setSvgText] = useState<string | null>(null)
  const [plotsMap, setPlotsMap] = useState<Record<string, Plot>>({})
  const containerRef = useRef<HTMLDivElement | null>(null)
  const [hovered, setHovered] = useState<{ plot?: Plot; x: number; y: number } | null>(null)

  // load SVG + plot data
  useEffect(() => {
    async function load() {
      try {
        // 1. Fetch SVG file
        const res = await fetch(svgFilePath)
        const txt = await res.text()
        setSvgText(txt)

        // 2. Fetch all plots for this layout (MongoDB doc has plots array)
        const layoutData = await get(`api/plots/layout/${layoutId}`)
        if (layoutData?.plots) {
          const map: Record<string, Plot> = {}
          for (const p of layoutData.plots) {
            map[p.plotId] = p
          }
          setPlotsMap(map)
        }
      } catch (err) {
        console.error('Error loading layout or plots:', err)
      }
    }

    load()
  }, [layoutId, svgFilePath])

  // attach listeners for hover + tap
  useEffect(() => {
    const root = containerRef.current
    if (!root) return
    let lastTarget: Element | null = null

    function handlePointer(e: PointerEvent | MouseEvent | TouchEvent) {
      const target = e.target as Element | null
      const id = target?.getAttribute?.('id')

      if (id && id.startsWith('plot-') && plotsMap[id]) {
        const clientX = 'clientX' in e ? e.clientX : (e as TouchEvent).touches[0].clientX
        const clientY = 'clientY' in e ? e.clientY : (e as TouchEvent).touches[0].clientY
        setHovered({ plot: plotsMap[id], x: clientX, y: clientY })

        if (lastTarget && lastTarget !== target) lastTarget.classList.remove('hovered')
        target?.classList.add('hovered')
        lastTarget = target
      } else {
        setHovered(null)
        if (lastTarget) {
          lastTarget.classList.remove('hovered')
          lastTarget = null
        }
      }
    }

    // desktop hover
    root.addEventListener('pointermove', handlePointer)
    // mobile tap
    root.addEventListener('click', handlePointer)

    return () => {
      root.removeEventListener('pointermove', handlePointer)
      root.removeEventListener('click', handlePointer)
    }
  }, [plotsMap])

  return (
    <div style={{ position: 'fixed', inset: 0, background: 'rgba(0,0,0,0.6)', zIndex: 1000 }}>
      <button
        onClick={onClose}
        style={{
          position: 'absolute',
          top: 20,
          right: 20,
          padding: '6px 12px',
          background: '#fff',
          borderRadius: 6
        }}
      >
        ✕ Close
      </button>

      <div
        style={{ width: '100%', height: '100%', display: 'grid', placeItems: 'center' }}
        ref={containerRef}
      >
        {svgText ? (
          <div
            style={{ width: '90%', height: '90%', overflow: 'auto' }}
            dangerouslySetInnerHTML={{ __html: svgText }}
          />
        ) : (
          <div style={{ color: '#fff' }}>Loading...</div>
        )}

        {hovered?.plot && (
          <div
            style={{
              position: 'fixed',
              top: hovered.y + 12,
              left: hovered.x + 12,
              pointerEvents: 'none',
              zIndex: 1100
            }}
          >
            <div
              style={{
                background: '#fff',
                padding: 8,
                borderRadius: 6,
                boxShadow: '0 6px 18px rgba(10,20,30,0.12)'
              }}
            >
              <div style={{ fontWeight: 700 }}>
                PlotNumber: {hovered.plot.number ?? hovered.plot.plotId}
              </div>
              <div style={{ fontSize: 12 }}>Dimension: {hovered.plot.dimension}</div>
              <div style={{ fontSize: 12 }}>Area: {hovered.plot.area}</div>
              <div style={{ fontSize: 12 }}>Direction: {hovered.plot.direction}</div>
              <div
                style={{
                  fontSize: 12,
                  color: hovered.plot.status === 'sold' ? 'red' : 'green'
                }}
              >
                {hovered.plot.status}
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}

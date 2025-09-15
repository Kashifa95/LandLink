import React, { useEffect, useState } from 'react'
import { get } from '../api/ApiService'

interface LayoutItem {
  layoutId: string
  name: string
  svgFilePath: string
}

export default function LayoutList({
  onOpen
}: {
  onOpen: (layoutId: string, svgFilePath: string) => void
}) {
  const [layouts, setLayouts] = useState<LayoutItem[]>([])

  useEffect(() => {
    get('api/svg/layouts')
      .then((data) => setLayouts(data))
      .catch(() => {
        // fallback sample
        setLayouts([
          {
            layoutId: 'sample-1234',
            name: 'TestLayout.svg',
            svgFilePath: '/public/demo-layout.svg'
          }
        ])
      })
  }, [])

  return (
    <div>
      <h2>Available Layouts</h2>
      <ul style={{ listStyle: 'none', padding: 0 }}>
        {layouts.map((l) => (
          <li key={l.layoutId} style={{ margin: '8px 0' }}>
            <button
              onClick={() => onOpen(l.layoutId, l.svgFilePath)}
              style={{
                background: 'none',
                border: 'none',
                cursor: 'pointer',
                color: '#2563eb',
                fontSize: 16,
                textDecoration: 'underline'
              }}
            >
              {l.name.replace(/\.svg$/i, '')}
            </button>
          </li>
        ))}
      </ul>
    </div>
  )
}

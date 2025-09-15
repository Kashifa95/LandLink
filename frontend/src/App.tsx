import React, { useState } from 'react'
import LayoutList from './components/LayoutList'
import LayoutViewer from './components/LayoutViewer'
import AdminPage from './components/Admin/AdminPage'

type Page = 'home' | 'admin'

export default function App() {
  const [page, setPage] = useState<Page>('home')
  const [openLayoutId, setOpenLayoutId] = useState<string | null>(null)
  const [svgFilePath, setSvgFilePath] = useState<string | null>(null)

  return (
    <div style={{ minHeight: '100vh', background: '#f7fafc' }}>
      <header style={{ padding: 16, borderBottom: '1px solid #e6edf3', display: 'flex', gap: 24 }}>
        <h1 style={{ margin: 0, flex: 1 }}>Interactive Layout Map</h1>
        <nav style={{ display: 'flex', gap: 12 }}>
          <button onClick={() => { setPage('home'); setOpenLayoutId(null) }}>Home</button>
          <button onClick={() => { setPage('admin'); setOpenLayoutId(null) }}>Admin</button>
        </nav>
      </header>

      <main style={{ padding: 16 }}>
        {page === 'home' && !openLayoutId && <LayoutList onOpen={(id, svgFilePath) => { setOpenLayoutId(id); setSvgFilePath(svgFilePath); }} />}
        {page === 'home' && openLayoutId && <LayoutViewer layoutId={openLayoutId}  svgFilePath={svgFilePath} onClose={() => { setOpenLayoutId(null); setSvgFilePath(null); }} />}
        {page === 'admin' && <AdminPage />}
      </main>
    </div>
  )
}

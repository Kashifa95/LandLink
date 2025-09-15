import React, { useState } from 'react'
import { postForm } from '../../api/ApiService'

export default function UploadLayout({ token }: { token: string }) {
  const [file, setFile] = useState<File | null>(null)
  const [preview, setPreview] = useState<string | null>(null)
  const [status, setStatus] = useState<string | null>(null)

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = e.target.files?.[0] || null
    setFile(selectedFile)
    setStatus(null)

    if (selectedFile) {
      // Create a preview URL for SVG
      const reader = new FileReader()
      reader.onload = () => {
        setPreview(reader.result as string)
      }
      reader.readAsText(selectedFile)
    } else {
      setPreview(null)
    }
  }

  const handleUpload = async () => {
    if (!file) {
      setStatus('Please select a file first')
      return
    }

    const formData = new FormData()
    formData.append('file', file) // 👈 backend expects this exact field

    try {
      await postForm('api/svg/upload', formData)
      setStatus('✅ Upload successful!')
    } catch (err: any) {
      console.error('Upload error:', err)
      setStatus(`❌ Upload failed: ${err.message}`)
    }
  }

  return (
    <div style={{ border: '1px solid #ddd', padding: 16, borderRadius: 6 }}>
      <h3>Upload Layout</h3>
      <input type="file" accept=".svg" onChange={handleFileChange} />

      {preview && (
        <div
          style={{
            marginTop: 12,
            padding: 8,
            border: '1px solid #ccc',
            borderRadius: 6,
            maxHeight: 300,
            overflow: 'auto',
            background: '#fafafa'
          }}
        >
          <div dangerouslySetInnerHTML={{ __html: preview }} />
        </div>
      )}

      <button
        onClick={handleUpload}
        style={{
          marginTop: 12,
          padding: '6px 12px',
          borderRadius: 6,
          background: '#2563eb',
          color: '#fff'
        }}
      >
        Upload
      </button>

      {status && <div style={{ marginTop: 8 }}>{status}</div>}
    </div>
  )
}

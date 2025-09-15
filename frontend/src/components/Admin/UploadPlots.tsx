import React, { useState } from 'react'
import Papa from 'papaparse'
import { postForm } from '../../api/ApiService'

export default function UploadPlots({ token }: { token: string }) {
  const [file, setFile] = useState<File | null>(null)
  const [preview, setPreview] = useState<any[]>([])
  const [msg, setMsg] = useState('')
  const [currentPage, setCurrentPage] = useState(1)
  const rowsPerPage = 10

  function handleFileChange(e: React.ChangeEvent<HTMLInputElement>) {
    const f = e.target.files?.[0]
    if (f) {
      setFile(f)
      Papa.parse(f, {
        header: true,
        skipEmptyLines: true,
        complete: (res) => setPreview(res.data as any[]),
      })
    }
  }

  function handleEdit(rowIndex: number, key: string, value: string) {
    const updated = [...preview]
    updated[rowIndex][key] = value
    setPreview(updated)
  }

  async function handleUpload() {
    if (!file) return setMsg('Please choose a CSV file')

    try {
      const formData = new FormData()
      formData.append('file', file) // 👈 same as Postman (key = file)

      const res = await postForm('api/plots/import', formData)

      // if (!res.ok) {
      //   throw new Error(await res.text())
      // }

      setMsg('✅ Uploaded successfully')
    } catch (err: any) {
      console.error('Upload error', err)
      setMsg(err.message || '❌ Upload failed')
    }
  }

  // pagination
  const totalPages = Math.ceil(preview.length / rowsPerPage)
  const startIndex = (currentPage - 1) * rowsPerPage
  const pageData = preview.slice(startIndex, startIndex + rowsPerPage)

  return (
    <div style={{ background: '#fff', padding: 12, borderRadius: 8 }}>
      <h3 style={{ marginTop: 0 }}>Upload plots CSV</h3>

      <input type="file" accept=".csv" onChange={handleFileChange} />

      {preview.length > 0 && (
        <>
          <table
            style={{
              width: '100%',
              borderCollapse: 'collapse',
              marginTop: 12,
            }}
          >
            <thead>
              <tr>
                {Object.keys(preview[0]).map((key) => (
                  <th
                    key={key}
                    style={{
                      border: '1px solid #ddd',
                      padding: '6px',
                      background: '#f3f4f6',
                    }}
                  >
                    {key}
                  </th>
                ))}
              </tr>
            </thead>
            <tbody>
              {pageData.map((row, i) => (
                <tr key={i}>
                  {Object.keys(row).map((key) => (
                    <td
                      key={key}
                      style={{
                        border: '1px solid #ddd',
                        padding: '6px',
                      }}
                    >
                      <input
                        value={row[key]}
                        onChange={(e) =>
                          handleEdit(startIndex + i, key, e.target.value)
                        }
                        style={{ width: '100%' }}
                      />
                    </td>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>

          {/* Pagination */}
          <div style={{ marginTop: 8 }}>
            <button
              disabled={currentPage === 1}
              onClick={() => setCurrentPage((p) => p - 1)}
            >
              Prev
            </button>
            <span style={{ margin: '0 8px' }}>
              Page {currentPage} of {totalPages}
            </span>
            <button
              disabled={currentPage === totalPages}
              onClick={() => setCurrentPage((p) => p + 1)}
            >
              Next
            </button>
          </div>

          <button
            type="button"
            onClick={handleUpload}
            style={{ marginTop: 12, padding: '6px 12px' }}
          >
            Upload
          </button>
        </>
      )}

      <div style={{ marginTop: 8, color: '#6b7280' }}>{msg}</div>
    </div>
  )
}

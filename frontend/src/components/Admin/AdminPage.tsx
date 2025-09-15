import React, { useState, useEffect } from "react"
import RegisterOwner from "./RegisterOwner"
import UploadLayout from "./UploadLayout"
import UploadPlots from "./UploadPlots"

export default function AdminPage() {
  const [token, setToken] = useState<string | null>(null)

  // ✅ Load token from localStorage on refresh
  useEffect(() => {
    const saved = localStorage.getItem("token")
    if (saved) setToken(saved)
  }, [])

  // ✅ Logout clears state + storage
  function handleLogout() {
    setToken(null)
    localStorage.removeItem("token")
  }

  return (
    <div style={{ display: "grid", gap: 16, maxWidth: 800, margin: "0 auto" }}>
      {!token ? (
        // 🔑 Show Register/Login form
        <RegisterOwner
          onSuccess={() => {
            const saved = localStorage.getItem("token")
            if (saved) setToken(saved)
          }}
        />
      ) : (
        // 📂 After login
        <>
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
            }}
          >
            <h2>Welcome, Landowner 🎉</h2>
            <button
              onClick={handleLogout}
              style={{
                padding: "6px 12px",
                borderRadius: 6,
                background: "#eee",
              }}
            >
              ⬅ Logout
            </button>
          </div>

          {/* File uploads with token */}
          <UploadLayout token={token} />
          <UploadPlots token={token} />
        </>
      )}
    </div>
  )
}

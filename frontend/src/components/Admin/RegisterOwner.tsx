import React, { useState } from "react"
import { post } from "../../api/ApiService"

export default function RegisterOwner({ onSuccess }: { onSuccess: () => void }) {
  const [tab, setTab] = useState<"login" | "register">("login")
  const [username, setUsername] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [msg, setMsg] = useState("")

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    try {
      const url = tab === "register" ? "auth/register" : "auth/login"
      const body =
        tab === "register" ? { username, email, password } : { username, password }

      const res = await post(url, body)

      if (res?.token) {
        localStorage.setItem("token", res.token)
        setMsg("✅ Success")
        onSuccess() // tell AdminPage login succeeded
      } else {
        setMsg("⚠️ No token received")
      }
    } catch (err: any) {
      setMsg("❌ " + (err.message || "Error"))
    }
  }

  return (
    <div style={{ background: "#fff", padding: 16, borderRadius: 8 }}>
      <h2>Landowner {tab === "login" ? "Sign In" : "Sign Up"}</h2>
      <div style={{ display: "flex", gap: 8, marginBottom: 12 }}>
        <button
          type="button"
          onClick={() => setTab("login")}
          style={{
            background: tab === "login" ? "#2563eb" : "#eee",
            color: tab === "login" ? "#fff" : "#000",
          }}
        >
          Sign In
        </button>
        <button
          type="button"
          onClick={() => setTab("register")}
          style={{
            background: tab === "register" ? "#2563eb" : "#eee",
            color: tab === "register" ? "#fff" : "#000",
          }}
        >
          Sign Up
        </button>
      </div>

      <form onSubmit={handleSubmit} style={{ display: "grid", gap: 12 }}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        {tab === "register" && (
          <input
            type="email"
            placeholder="Email Address"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        )}
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />

        <button type="submit">{tab === "login" ? "Sign In" : "Sign Up"}</button>
      </form>
      {msg && <div style={{ marginTop: 8, color: "#6b7280" }}>{msg}</div>}
    </div>
  )
}

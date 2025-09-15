// api/ApiService.ts
const API_BASE = "http://localhost:8080/"

function getToken() {
  return localStorage.getItem("token") || undefined
}

function authHeaders(token?: string) {
  const headers: any = {}
  const authToken = token || getToken()
  if (authToken) headers["Authorization"] = `Bearer ${authToken}`
  return headers
}

export async function get(path: string) {
  const res = await fetch(`${API_BASE}${path}`)
  const contentType = res.headers.get("content-type") || ""

  let data: any
  if (contentType.includes("application/json")) {
    data = await res.json()
  } else {
    data = await res.text()
  }

  if (!res.ok) throw new Error(typeof data === "string" ? data : "Request failed")
  return data
}

export async function post(path: string, body: any, token?: string) {
  const res = await fetch(`${API_BASE}${path}`, {
    method: "POST",
    headers: { "Content-Type": "application/json", ...authHeaders(token) },
    body: JSON.stringify(body),
  })
  const contentType = res.headers.get("content-type") || ""

  let data: any
  if (contentType.includes("application/json")) {
    data = await res.json()
  } else {
    data = await res.text()
  }

  if (!res.ok) throw new Error(typeof data === "string" ? data : "Request failed")
  return data
}

export async function postForm(path: string, formData: FormData, token?: string) {
  const res = await fetch(`${API_BASE}${path}`, {
    method: "POST",
    headers: authHeaders(token), // ✅ only auth here, no content-type
    body: formData,
  })
  const contentType = res.headers.get("content-type") || ""

  let data: any
  if (contentType.includes("application/json")) {
    data = await res.json()
  } else {
    data = await res.text()
  }

  if (!res.ok) throw new Error(typeof data === "string" ? data : "Upload failed")
  return data
}

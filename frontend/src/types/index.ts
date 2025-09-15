export type Plot = {
  area: string
  dimension: string
  plotId: string
  direction: string
  number?: string
  sizeSqM?: number
  price?: number
  status?: 'available'|'sold'|'reserved'
  ownerName?: string
  meta?: Record<string,any>
}

export type Layout = {
  _id: string
  name: string
  svgFilePath: string
  thumbnailUrl?: string
  metadata?: Record<string,any>
   plots: Plot[]
}

export type User = { id:string; name:string; email:string; role:string }

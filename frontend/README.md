# Interactive Layout Map - Frontend (minimal scaffold)

This is a minimal scaffold of the frontend project (React + TypeScript + Vite) described to you.
It includes vanilla-extract for styling and basic components:
- Layout list
- Layout viewer (inline SVG + hover tooltip)
- Admin upload components (register, upload SVG, upload CSV)

To run:
1. `npm install`
2. `npm run dev`
3. Open http://localhost:5173

Notes:
- This scaffold assumes a backend API under VITE_API_BASE or same origin.
- SVGs should include `data-plot-id` attributes for interactive plots.

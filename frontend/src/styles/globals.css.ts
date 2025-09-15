import { globalStyle } from '@vanilla-extract/css'
import { vars } from './theme.css'

globalStyle('html,body,#root', {
  height: '100%',
  margin: 0,
  fontFamily: 'Inter, system-ui, -apple-system, "Segoe UI", Roboto, sans-serif',
  background: vars.color.background,
  color: vars.color.text
})

globalStyle('svg .hovered', {
  outline: '2px solid ' + vars.color.primary,
  fillOpacity: '0.85'
})

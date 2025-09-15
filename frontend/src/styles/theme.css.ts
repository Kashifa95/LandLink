import { createGlobalTheme } from '@vanilla-extract/css'

export const vars = createGlobalTheme(':root', {
  color: {
    background: '#FFFFFF',
    surface: '#F9FAFB',
    primary: '#0B84FF',
    accent: '#F59E0B',
    text: '#111827',
    muted: '#6B7280',
  },
  space: { xs: '4px', sm: '8px', md: '16px' }
})

import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { Random } from './Random'
import { Box, Container, TextField, Typography } from '@mui/material'

function App() {
  const [emailContent,setEmailContent] =useState('');
  const [tone,setTone] = useState('');
  const [generatedReply,setGeneratedReply] = useState('');
  const [loading,setLoading] = useState('');
  const [errors,setErrors] = useState('');
  return (
    <>
      <Container maxWidth="sm" sx={{py:5}}>
        <Typography variant='h3' component='h1' gutterBottom>
          Email generator
        </Typography>
        <Box sx={{mx:2}}>
          <TextField  
          fullWidth
          multiline
          rows={6}
          variant="outlined"
          label= "Original Email Content"
          value={emailContent || ''}
          onChange={(e) => setEmailContent(e.target.value)}
          sx={{mb:2}}
          />
        </Box>
      </Container>
    {/* <div className='prachi'>
      <h1>"Hi"</h1>
    </div> */}

    </>
  )
}

export default App

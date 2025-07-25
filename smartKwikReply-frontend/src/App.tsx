import { useState } from 'react'
import './App.css'

import { Box, Button, CircularProgress, Container, FormControl, InputLabel, MenuItem, Select, TextField, Typography } from '@mui/material'
import axios from 'axios'

function App() {
  const [emailContent,setEmailContent] =useState('');
  const [tone,setTone] = useState('');
  const [generatedReply,setGeneratedReply] = useState('');
  const [loading,setLoading] = useState(false);
  const [errors,setErrors] = useState('');
  axios.defaults.baseURL = "http://localhost:9000"; // Replace with your backend URL
  axios.defaults.withCredentials = true;
  const handleSubmit = async ()=>{

    setLoading(true)
    setErrors('')
    try {
      const res= 
        await axios.post("/api/email-reply/reply",{content: emailContent,tone} );
        setGeneratedReply(typeof res.data === 'string'? res.data : JSON.stringify(res.data))
      }
      
     catch (error) {
      setErrors("Failed to generate reply.Please try again..")
          console.error(error)

    }
    finally{
      setLoading(false)
    }
  }
  
  return (
    <>
      <Container maxWidth="sm" sx={{py:5}}>
        <Typography variant='h3' component='h1' gutterBottom>
          Email Reply generator
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
          <FormControl fullWidth sx={{mb:2}}>
            <InputLabel>Tone 
            </InputLabel>
            <Select 
              value={tone || ''} 
              label= "Tone" 
              onChange={(e) => setTone(e.target.value)}>
                <MenuItem value="professional">Professional</MenuItem>
                <MenuItem value="casual">Casual</MenuItem>
                <MenuItem value="friendly">Friendly</MenuItem>
                <MenuItem value="">None</MenuItem>
            </Select>
          
          </FormControl> 
          <Button onClick={handleSubmit} disabled={!emailContent || loading} fullWidth>
            {loading ? <CircularProgress size={24}/> : "Generate Reply"}</Button>
        </Box>
        {errors && (
          <Typography color='error' sx={{mb:2}}>
          {errors}
        </Typography>
        )}
        {generatedReply && (
            <Box sx={{mt:3}}>
              <Typography variant='h6' gutterBottom>
                Generated Reply:
              </Typography>
               <TextField  
                  fullWidth
                  multiline
                  rows={6}
                  variant="outlined"
                  value={generatedReply || ''}
                  inputProps={{readOnly:true}}
                  onChange={(e) => setGeneratedReply(e.target.value)}
                  sx={{mb:2}}
                />
               
                <Button onClick={()=>navigator.clipboard.writeText(generatedReply)}>Copy to Clipboard</Button>
            </Box>
        )}
      </Container>
   

    </>
  )
}

export default App

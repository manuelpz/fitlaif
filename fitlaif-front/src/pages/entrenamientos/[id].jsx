import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'

export default function EntrenamientoFuncion() {
  const router = useRouter()
  const { id } = router.query
  const [entrenamientos, setEntrenamientos] = useState()
  const [isLoading, setIsLoading] = useState(true)
  const [error, setError] = useState(false)

  useEffect(() => {
    const fetchEntrenamientos = async () => {
      try {
        const response = await fetch(`http://localhost:8080/entrenamientos/${id}`)
        const data = await response.json()
        setEntrenamientos(data)
        setIsLoading(false)
      }
      catch (error) {
        setError(true)
        setIsLoading(false)
      }
    }
    if (id) {
      fetchEntrenamientos()
    }
  }, [id])

  useEffect(() =>{
    const fetchEjercicios = async () => {
      try {
        const response = await fetch(`http://localhost:8080/entrenamientos/${id}`)
        const data = await response.json()
        setData(data)
        setIsLoading(false)
      }
      catch (error) {
        setError(true)
        setIsLoading(false)
      }
    }
    if (id) {
      fetchEjercicios()
    }
  }, [id])


  if (isLoading) {
    return <div>Cargando resultados...</div>
  }

  if (error) {
    return <div>Estamos teniendo problemas para acceder a este entrenamiento, lo arreglaremos lo antes posible, disculpa las molestias</div>
  }
  return (
    <div>
      {entrenamientos?.musculo ? (<div><h1>¿Listo para tu entrenamiento de {entrenamientos.musculo.toLowerCase()}?</h1><br />
        <h2>¡A reventarlo!</h2></div>) : entrenamientos?.message}
    </div>
  )
}

import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'

export default function EntrenamientoFuncion() {
  const router = useRouter()
  const { id } = router.query
  const [data, setData] = useState<Entrenamiento | undefined>()
  const [isLoading, setIsLoading] = useState(true)

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(`http://localhost:8080/entrenamientos/${id}`)
      const data = await response.json()
      setData(data)
      setIsLoading(false)
    }

    if (id) {
      fetchData()
    }
  }, [id])

  if (isLoading) {
    return <div>Loading...</div>
  }

  if (!data) {
    return <div>No se encontró el entrenamiento.</div>
  }

  return (
    <div>
      <h1>El entrenamiento es {data.musculo.toLowerCase()}</h1>
    </div>
  )
}

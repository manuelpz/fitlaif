import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'

export default function EntrenamientoFuncion() {
  const router = useRouter()
  const { id } = router.query
  const [isMounted, setIsMounted] = useState(false)
  const [data, setData] = useState<Entrenamiento | ''>('')

  const fetchData = async () => {
    const response = await fetch(`http://localhost:8080/entrenamientos/${id}`)
    const data = await response.json()
    setData(data)
  }

  useEffect(() => {
    fetchData()
    setIsMounted(true)
  }, [id])

  if (!isMounted) {
    return null
  }
  return (
    <div>
      <h1>El entrenamiento es {data ? data.musculo.toLowerCase() : ''}</h1>
    </div>
  )
}

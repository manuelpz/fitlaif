import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'
import entrenamientos from '../../json/entrenamientos.json'



export default function EntrenamientoFuncion() {
  const router = useRouter()
  const { id } = router.query
  const [data, setData] = useState<Entrenamiento | ''>('')

  useEffect(() => {
    entrenamientos.forEach((e: Entrenamiento) => {
      if (id !== null && id !== undefined && id === String(e.id)) {
        setData(e)
      }
    })
  }, [id])

  return (
    <div>
      <h1>El entrenamiento es {data ? data.musculo.toLowerCase() : ''}</h1>
    </div>
  )
}

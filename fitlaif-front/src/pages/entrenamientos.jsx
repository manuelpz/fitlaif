import Image from 'next/image'
import Link from 'next/link'
import { useEffect, useState } from 'react'
export default function Entrenamientos() {
  const [isMounted, setIsMounted] = useState(false)
  const [data, setData] = useState([])

  const fetchData = async () => {
    const response = await fetch('http://localhost:8080/entrenamientos')
    const data = await response.json()
    setData(data)
  }
  useEffect(() => {
    fetchData()
    setIsMounted(true)
  }, [])

  if (!isMounted) {
    return null
  }

  return (
    <div>
      <h1>¿Qué vamos a entrenar hoy?</h1>
      <div className="max-w-sm rounded overflow-hentrenamientoIdden shadow-lg">
        {data.map((e) => (
          <Link
            href={`/entrenamientos/${e.entrenamientoId}`}
            key={e.entrenamientoId}
          >
            <div key={e.entrenamientoId} suppressHydrationWarning={true}>
              <Image
                className="w-full"
                src={e.img}
                width={200}
                height={200}
                alt="Entrenamiento"
              />
              <div className="px-6 py-4">
                <div className="font-bold text-xl mb-2">{e.musculo}</div>
                <p className="text-gray-700 text-base">Breve descripcion</p>
              </div>
              <div className="px-6 pt-4 pb-2">
                <span className="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">
                  {`#${e.hashtag[0]}`}
                </span>
                <span className="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">
                  {`#${e.hashtag[1]}`}
                </span>
                <span className="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">
                  {`#${e.hashtag[2]}`}
                </span>
              </div>
            </div>
          </Link>
        ))}
      </div>
    </div>
  )
}

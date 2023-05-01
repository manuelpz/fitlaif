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
    <div className="container mx-auto">
      <h1 className='text-center'>¿Qué vamos a entrenar hoy?</h1>
      <div className="grid grid-cols-2 gap-4 ">
        {data.map((e) => (
          <Link
            href={`/entrenamientos/${e.entrenamientoId}`}
            key={e.entrenamientoId}
          >
            <div className="max-w-md mx-auto rounded overflow-hidden shadow-lg hover:shadow-xl transition duration-500 !bg-gray-900" key={e.entrenamientoId} suppressHydrationWarning={true}>
              <div className="relative h-auto">
                <Image
                  className="w-full h-auto"
                  width={400}
                  height={400}
                  src={e.img}
                  alt="Entrenamiento"
                />
              </div>
              <div className="px-6 py-4">
                <div className="font-bold text-xl mb-2">{e.musculo}</div>
                <p className="text-white text-base">Breve descripcion</p>
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

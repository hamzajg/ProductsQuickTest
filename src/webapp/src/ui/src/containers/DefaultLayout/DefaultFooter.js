import React from 'react'
import { CFooter } from '@coreui/react'

const DefaultFooter = () => {
  return (
    <CFooter fixed={false}>
      <div>
        <a href="https://hamzajg.github.io" target="_blank" rel="noopener noreferrer">Hamza JGUERIM</a>
        <span className="ml-1">&copy; 2020.</span>
      </div>
      <div className="mfs-auto">
        <span className="mr-1">Powered by</span>
        <a href="https://hamzajg.github.io" target="_blank" rel="noopener noreferrer">Hamza JGUERIM</a>
      </div>
    </CFooter>
  )
}

export default React.memo(DefaultFooter)
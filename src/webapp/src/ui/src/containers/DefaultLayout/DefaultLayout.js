import React from 'react'
import {
    DefaultContent,
    DefaultSidebar,
    DefaultFooter,
    DefaultHeader
} from './index'

const DefaultLayout = () => {

  return (
    <div className="c-app c-default-layout">
      <DefaultSidebar/>
      <div className="c-wrapper">
        <DefaultHeader/>
        <div className="c-body">
          <DefaultContent/>
        </div>
        <DefaultFooter/>
      </div>
    </div>
  )
}

export default DefaultLayout;

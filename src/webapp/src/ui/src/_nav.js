import React from 'react'
import CIcon from '@coreui/icons-react'

const _nav =  [
  {
    _tag: 'CSidebarNavItem',
    name: 'Home',
    to: '/',
    icon: <CIcon name="cil-speedometer" customClasses="c-sidebar-nav-icon"/>,
    badge: {
      color: 'info',
      text: 'NEW',
    }
  },
  {
    _tag: 'CSidebarNavItem',
    name: 'Categories',
    to: '/product-categories'
  },
  {
    _tag: 'CSidebarNavItem',
    name: 'Products',
    to: '/products'
  },
  {
    _tag: 'CSidebarNavItem',
    name: 'Customers',
    to: '/customers'
  },
]
export default _nav

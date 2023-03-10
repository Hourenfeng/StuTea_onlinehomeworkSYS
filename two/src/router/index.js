import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Background from '../views/background/Background.vue'
import Teacher from '../views/teacher/Teacher.vue'
import Students from '../views/student/Students.vue'
import Resources from '../views/resource/Resources.vue'
import Schools from '../views/school/Schools.vue'
import Thomeworks from '../views/homework/Thomeworks.vue'
import Shomeworks from '../views/homework/Shomeworks.vue'
import UploadHomework from '../views/student/UploadHomework.vue'
import Main from '../views/main/Main.vue'
import StudentHomepage from '../views/student/Student_Homepage.vue'
import Login from '../views/user/Login.vue'
import UserReg from '../views/user/UserReg.vue'
import TeacherHKList from '../views/teacher/Tea_homeworkList.vue'
import StudentHKList from '../views/teacher/Stu_homeworkLisk.vue'
import OneStudentHKList from '../views/teacher/OneStu_homework.vue'
import TeaHomepage from '../views/teacher/Tea_Homepage.vue'
import TeacherReList from '../views/teacher/Tea_Resources.vue'
import FilePreview from '../views/teacher/File_preview.vue'
import StuPersonal from '../views/Personal/StuPersonal.vue'
import TeaPersonal from '../views/Personal/TeaPersonal.vue'
import TeaInfo from '../views/Personal/TeaInfo.vue'
import StuInfo from '../views/Personal/StuInfo.vue'
import TeaRemind from '../views/Personal/TeaRemind.vue'
import TeaPassword from '../views/Personal/TeaPassword.vue'
import StuPassword from '../views/Personal/StuPassword.vue'
import ClazzStuControl from '../views/teacher/ClazzStuControl.vue'
import ClazzControl from'../views/teacher/ClazzControl.vue'
import OnlineDraw from '../views/onlineDraw/OnlineDraw.vue'
import StuDetail from '../views/teacher/StuDetail.vue'
import Tdraw from '../views/tdraw.vue'
import TeadataAnalyse from "../views/teacher/TeadataAnalyse.vue"
import StudataAnalyse from "../views/student/StudataAnalyse"
const routes = [
	{
		path:'/tdraw',
		component:Tdraw
	},
	{
		path:'/Regist',
		component:()=>import('@/views/user/Regist')
	},
	{
		path:'/index',
		component:()=>import('@/views/canvas/index')
	},
	{
		path:'/img',
		component:()=>import('@/views/imgDraw/imgDraw')
	},
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
	  path:'/test',
	  compoent:()=>import('@/views/common/test')
  },
  {
	  path:'/filePreview',
	  name:'FilePreview',
	  component:FilePreview,
  },
  {
	  path:'/onlineDraw',
	  name:'OnlineDraw',
	  component:OnlineDraw,
  },
  {
  	  path:'/tdraw',
  	  name:'Tdraw',
  	  component:Tdraw,
  },
  {//?????????
	  path:'/teaHomepage',
	  name:'TeaHomepage',
	  component:TeaHomepage,
	  meta:{
		  title:'??????',
	  },
	  children:[
		  {//?????????
		  	  path:'/teahklist',
		  	  name:'TeacherHKList',
		  	  component:TeacherHKList,
			  meta:{
				  title:'??????????????????',
			  },
			  children:[
				  {//?????????
				  	path:'stuhkList',
				  	name:'StudentHKList',
				  	component:StudentHKList,
					meta:{
						title:'????????????',
					},
					children:[
						{//?????????
							path:'oneStuhkList',
							name:'OneStudentHKList',
							component:OneStudentHKList,
							meta:{
								title:'????????????',
							},
						},
					]
				  },
				  {
				  					  path:'addHomework',
				  					  name:'AddHomework',
				  					  component:()=>import('@/views/teacher/Tea_addHomework'),
				  					  meta:{
				  						  title:'????????????'
				  					  }
				  				  },
				  				  {
				  					  path:'editHomework',
				  					  name:'EditHomework',
				  					  component:()=>import('@/views/teacher/Tea_EditHomework'),
				  					  meta:{
				  						  title:'????????????'
				  					  }
				  				  }
			  ]
		  },
			{
				path:'/teacherReList',
				name:'TeacherReList',
				component:TeacherReList,
				meta:{
					title:'????????????'
				}
			},
			{//?????????2
				  	  path:'/clazzControl',
				  	  name:'ClazzControl',
				  	  component:ClazzControl,
					  meta:{
					  	title:'????????????',
					  },children:[
					{
						path:'clazzStuControl',
						name:'ClazzStuControl',
						component:ClazzStuControl,
						meta:{
							title:'??????????????????',
						
						},children:[
							{
								path:'stuDetail',
								name:'StuDetail',
								component:StuDetail,
								meta:{
									title:'??????????????????',
								}
							}
						]
					},
				]
				  },
		  {
			  path:'/teadataAnalyse',
			  name:'TeadataAnalyse',
			  component:TeadataAnalyse,
			  meta:{
				  title:'????????????'
			  }

		  }
	  ]
  },
  {
	  path:'/login',
	  name:'Login',
	  component:Login
  },
  {
	  path:'/userReg',
	  name:'UserReg',
	  component:UserReg
  },
{
	path:'/studentHomepage',
	name:'StudentHomepage',
	component:StudentHomepage,
	meta:{
		title:'??????',
	},
	children:[
		{
			path:'/studenthkList',
			name:'StudenthkList',
			component:() => import('@/views/student/Student_homeworkList'),
			meta:{
				title:'????????????'
			},
			children:[
				{
					path:'studentUploadHK',
					name:'StudentUploadHK',
					component:()=>import('@/views/student/Student_UploadHK'),
					meta:{
						title:'????????????',
					}
				},
			]
		},
		{
			path:'/studentResources',
			name:'StudentResources',
			component:()=>import('@/views/student/Student_Resources'),
			meta:{
				title:'????????????',
			}
		},
		{
			path:'/studataAnalyse',
			name:'StudataAnalyse',
			component:()=>import('@/views/student/StudataAnalyse'),
			meta:{
				title:'????????????',
			}

		}
	]
},
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
  	path:'/uploadHomework',
  	name:'UploadHomework',
  	component:UploadHomework
  },
  {
	  path:'/main',
	  name:'Main',
	  component:Main
  },
  {
	  path:'/background',
	  name:'Background',
	  component:Background,
	  children:[
		  {
			  path:'/teacher',
			  name:'Teacher',
			  component:Teacher
		  },
		  {
			  path:'/students',
			  name:'Students',
			  component:Students
		  },
		  {
			  path:'/resources',
			  name:'Resources',
			  component:Resources
		  },
		  {
			  path:'/schools',
			  name:'Schools',
			  component:Schools
		  },
		  {
			  path:'/thomeworks',
			  name:'Thomeworks',
			  component:Thomeworks
		  },
		  {
			  path:'/shomeworks',
			  name:'Shomeworks',
			  component:Shomeworks
		  }
		]
  },
	{
		path: '/StuPersonal',
		name:'StuPersonal',
		component: StuPersonal,
		children: [
			{
				path: '/StuInfo',
				name: 'StuInfo',
				component: StuInfo
			},
			{
				path: '/StuPassword',
				name: 'StuPassword',
				component: StuPassword
			}
		]
	},
	{
		path: '/TeaPersonal',
		name:'TeaPersonal',
		component: TeaPersonal,
		children: [
			{
				path: '/TeaInfo',
				name: 'TeaInfo',
				component: TeaInfo
			},
			{
				path: '/TeaPassword',
				name: 'TeaPassword',
				component: TeaPassword
			},
			{
				path: '/TeaRemind',
				name: 'TeaRemind',
				component: TeaRemind
			}
		]
	}

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

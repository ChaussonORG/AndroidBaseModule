# AndroidBaseModule
速道科技有限公司安卓基础组件

### base
1. BaseActivity,BaseFragment,BaseView 基类
2. BundleConst 常量，请修改 app\_name，base\_url
3. BaseApplication ，初始化应用相关内容

###basicapi（基础API）
1. 登录，注册，修改密码，找回密码，绑定手机号相应接口和Activity已经写好，只需要更改相应xml布局即可（注意：对应控件id不能更改）
2. 意见反馈，获取验证码，使用时创建BaseFunctionAPI对象，然后设置相应监听器


###common
+ listview
  ListView,RecyclerView的通用Adapter，RecyclerView的分割线（DividerItemDecoration）

+ util
  1. AppHelper:获取APP版本名，检查网络连接等
  2. AppManager:Activity管理类
  3. BitmapHelper:图像处理帮助类
  4. ClickUtil:控制频繁点击
  5. CustomGlideModule:处理Glide缓存
  6. GsonHelper:json工具类
  7. ImageHelper:图片加载（使用Glide），包括普通图片，圆角矩形图片，圆形图片
  8. LogUtil:Log工具类
  9. ScreenHelper:获取屏幕宽度和高度等
  10. SPHelper:SharePreference工具类
  11. StringUtil:处理字符串，如校验手机号码等
  12. ToastHelper:Toast工具类
  13. ToolbarUtil:toolbar 工具类
  14. UMShareHelper:友盟分享工具类
  
+ view(自定义view）
  1. loadmore:下拉刷新和加载更多的布局
  2. countdown：倒计时（获取手机验证码页面）
  3. CustomScrollView:嵌套recyclerview，解决滑动冲突和滑动惯性消失的问题
  
+ viewpager
  1. 不能滑动的viewpager
  2. 自动切换的viewpager
  3. viewpager指示器 

###component
+ dialog
  弹出式对话框，预置了两种样式（iOS和Uber）
+ file
  文件上传和下载
+ filterrender
  自定义的下拉列表
+ flowlayout
  流式布局，如热门标签
+ pickimage
  选择照片（从相册中选择和拍照）
+ previewfile
  预览文件，目前支持PDF和图片

###http
+ 网络请求框架，使用Retrofit + OKHTTP
+ 已经封装好缓存和Cookie
+ 通用的请求回调

###other
+ AndroidGitignore：Gitignore忽略文件，必须在创建工程之前上传至 Git 才起作用
+ GradleReference：Gradle基础配置，包括多渠道打包


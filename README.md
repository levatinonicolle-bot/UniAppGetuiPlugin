# GetuiPush UniApp原生插件

个推SDK的UniApp原生插件，支持离线消息推送。

## 功能特性

- ✅ 离线消息推送
- ✅ 在线消息透传
- ✅ 通知栏显示
- ✅ 点击通知跳转
- ✅ Android 8+ 通知渠道
- ✅ Android 13+ 权限适配

## 项目结构

```
UniAppGetuiPlugin/
├── .github/
│   └── workflows/
│       └── build.yml          # GitHub Actions自动构建配置
├── android/
│   ├── src/                   # Java源码
│   ├── libs/                  # 个推SDK依赖
│   ├── build.gradle           # Gradle构建配置
│   └── gradlew                # Gradle启动脚本
├── package.json               # 插件配置
└── README.md                  # 本文件
```

## 自动构建

本项目配置了GitHub Actions自动构建，每次推送代码后会自动编译AAR文件。

### 下载编译好的AAR

1. 进入仓库的 [Actions](../../actions) 页面
2. 点击最新的构建任务
3. 在页面底部下载 `GetuiPush-AAR`
4. 解压得到 `GetuiPush.aar`

### 手动触发构建

1. 进入 [Actions](../../actions) 页面
2. 点击左侧 "Build Android AAR"
3. 点击 "Run workflow" 按钮

## 本地构建

如果需要本地编译：

```bash
cd android
./gradlew assembleRelease
```

编译完成后，AAR文件位于：
```
android/build/outputs/aar/android-release.aar
```

## 使用方法

### 1. 安装插件

将编译好的 `GetuiPush.aar` 放入你的UniApp项目：

```
nativeplugins/
└── GetuiPush/
    ├── android/
    │   └── GetuiPush.aar
    └── package.json
```

### 2. 配置manifest.json

```json
{
  "app-plus": {
    "nativePlugins": {
      "GetuiPush": {
        "GETUI_APPID": "你的个推AppID"
      }
    }
  }
}
```

### 3. 使用插件

```javascript
// 引入插件
const getuiModule = uni.requireNativePlugin('GetuiPush-GetuiPushModule');

// 获取CID
getuiModule.getClientId((ret) => {
  console.log('个推CID:', ret.cid);
});

// 监听推送消息
uni.onPushMessage((data) => {
  console.log('收到推送:', data);
});
```

## 版本信息

- 个推SDK版本: 3.3.16.0
- 适用UniApp版本: 3.x
- 最低Android版本: 5.0 (API 21)
- 目标Android版本: 11.0 (API 30)

## 获取个推凭证

1. 访问 [个推开发者平台](https://dev.getui.com/)
2. 注册并创建应用
3. 获取 AppID、AppKey、MasterSecret

## 注意事项

- 必须使用自定义基座或云打包，标准基座不包含原生插件
- Android 13+ 需要申请 `POST_NOTIFICATIONS` 权限
- 推荐创建通知渠道以自定义通知样式

## 许可证

MIT License

## 相关链接

- [个推官网](https://www.getui.com/)
- [UniApp官网](https://uniapp.dcloud.io/)
- [个推开发者文档](https://docs.getui.com/)

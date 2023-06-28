# View AttachToWindow CoroutineScope

[![jitpack](https://jitpack.io/v/liu-wanshun/view-attach-window-coroutine-scope.svg)](https://jitpack.io/#liu-wanshun/view-attach-window-coroutine-scope)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

参考`AndroidX-KTX`中的`lifecycleScope`,将`CoroutineScope`绑定到`View`的`attachToWindow` ~ `detachedFromWindow`期间范围。

Rxjava版本请查看：https://github.com/liu-wanshun/AndroidDisposable

## 添加依赖

1. 添加`jitpack`仓库

```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

2. 添加`view-attach-window-coroutine-scope`
   依赖最新版（将Tag替换为[![jitpack](https://jitpack.io/v/liu-wanshun/view-attach-window-coroutine-scope.svg)](https://jitpack.io/#liu-wanshun/view-attach-window-coroutine-scope)
   后面的数字）

```groovy
dependencies {
    implementation "com.github.liu-wanshun:view-attach-window-coroutine-scope:Tag"
}
```

## 用法

```kotlin
val view: View = findViewById<View>(R.id.test)
// 注意：
// 仅在attachToWindow ~ detachedFromWindow 范围期间获取的attachWindowScope可以执行
// 在onDetachedFromWindow后cancel
view.attachWindowScope.launch {

}
// 实际常在自定义View内部使用,一般不在View外部使用
```
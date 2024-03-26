# Great Scrollable Tooltips
允许滚动物品栏中的物品工具提示（Lore）。

# Great 前缀是什么意思？
仅仅是为了与其他已经叫作 Scrollable Tooltips 区分，避免 ModID 冲突。

参考了曾经居住过的国家 “Great Britain”。

# 用法
将 Mod 文件放入 mods 目录中，将鼠标指针对准一个物品，滑动鼠标滚轮即可滚动物品工具提示。

部分版本支持您按住 Shift 并使用鼠标滚轮来实现水平滚动。

部分版本支持您通过绑定的按键来上左下右进行滚动，它们可以在按键绑定中修改。

# 配置方法
如果您正在使用 Forge 版本，请进入 Mod 页面，找到 Great Scrollable Tooltips 并点击 “config”。

如果您正在使用 Fabric 版本，请安装 Mod [模组菜单](https://modrinth.com/mod/modmenu)，并在模组菜单页面中找到 Great Scrollable Tooltips 并点击配置图标。

# 签名验证
部分版本有数字签名，建议您在非本仓库下载时进行签名验证，验证方法如下：

```
jarsigner -verify -verbose -certs great-scrollable-tooltips-<版本>.jar
```

出现下方内容表示验证成功：

```
sm      2041 Thu Jan 01 08:00:00 CST 1970 org/spongepowered/asm/lib/util/package.html

      >>> 签名者
      X.509, CN=flowerinsnow.online
      Signature algorithm: SHA256withRSA, 256 位密钥
      [证书将在2024/5/31 下午6:49到期]
      [ExtendedKeyUsage 扩展不支持代码签名]
      X.509, CN=GTS CA 1P5, O=Google Trust Services LLC, C=US
      Signature algorithm: SHA256withRSA, 2048 位密钥
      [证书的有效期为2020/8/13 上午8:00至2027/9/30 上午8:00]
      X.509, CN=GTS Root R1, O=Google Trust Services LLC, C=US
      Signature algorithm: SHA256withRSA, 4096 位密钥
      [证书的有效期为2020/6/19 上午8:00至2028/1/28 上午8:00]


  s = 已验证签名
  m = 在清单中列出条目
  k = 在密钥库中至少找到了一个证书

- 由 "CN=flowerinsnow.online" 签名
    摘要算法: SHA-256
    签名算法: SHA256withECDSA, 256 位密钥

jar 已验证。

警告:
此 jar 包含由于签名者证书的 ExtendedKeyUsage 扩展而无法进行代码签名的条目。
此 jar 包含签名者证书将在六个月内过期的条目。
此 jar 包含的签名没有时间戳。如果没有时间戳, 则在其中任一签名者证书到期 (最早为 2024-05-31) 之后, 用户可能无法验证此 jar。
```

**警告**：请验证签名者，签名者一定是 CN=flowerinsnow.online。

**警告**：出现下面这些内容表示验证失败，如果是从非本仓库下载的文件，请勿使用！

1. 当签名者 CN 不为 `flowerinsnow.online` 时，表示签名者不是当前作者。
2. 当出现类似 `jarsigner: java.lang.SecurityException: SHA-256 digest error for online/flowerinsnow/greatscrollabletooltips/GreatScrollableTooltips.class` 等字样时，表示 jar 文件被修改过。
3. 当出现类似 `此 jar 包含其证书链无效的条目。原因: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target` 或 `此 jar 包含其签名者证书为自签名证书的条目。` 等字样时，表示该副本是由未知签名者签发的。
4. 当出现类似 `jar 未签名。` 等字样时，表示该副本未被签名。

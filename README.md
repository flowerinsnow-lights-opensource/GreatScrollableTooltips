# Languages
[简体中文](README/zh_cn.md)

# GreatScrollableTooltips
Allow scrolling of item tooltips in inventory (Lore).

# What does the Great prefix mean?
Just to distinguish it from other already called Scrollable Tooltips to avoid ModID conflicts.

Reference to the country where I once lived, "Great Britain".

# Usage
Put the mod file into the mods directory, point the mouse pointer at an item, and slide the mouse wheel to scroll the item tooltip.

Some versions allow you to hold down Shift and use your mouse wheel to scroll horizontally.

Some versions allow you to scroll through bound keys, which can be changed in key bindings.

# How to configure
If you are using the Forge version, go to the Mod page, find Great Scrollable Tooltips and click "config".

If you are using the Fabric version, please install Mod [Mod Menu](https://modrinth.com/mod/modmenu), find Great Scrollable Tooltips in the Mod Menu page and click the configure icon.

# Signature verification
Some versions have digital signatures. It is recommended that you perform signature verification when downloading from a non-repository. The verification method is as follows:

```
jarsigner -verify -verbose -certs great-scrollable-tooltips-<version>.jar
```

The following content appears to indicate successful verification:

```
sm      2041 Thu Jan 01 08:00:00 CST 1970 org/spongepowered/asm/lib/util/package.html

      >>> Signer
      X.509, CN=flowerinsnow.online
      Signature algorithm: SHA256withRSA, 256-bit key
      [certificate will expire on 5/31/24, 6:49 PM]
      [ExtendedKeyUsage extension does not support code signing]
      X.509, CN=GTS CA 1P5, O=Google Trust Services LLC, C=US
      Signature algorithm: SHA256withRSA, 2048-bit key
      [certificate is valid from 8/13/20, 8:00 AM to 9/30/27, 8:00 AM]
      X.509, CN=GTS Root R1, O=Google Trust Services LLC, C=US
      Signature algorithm: SHA256withRSA, 4096-bit key
      [certificate is valid from 6/19/20, 8:00 AM to 1/28/28, 8:00 AM]


  s = signature was verified 
  m = entry is listed in manifest
  k = at least one certificate was found in keystore

- Signed by "CN=flowerinsnow.online"
    Digest algorithm: SHA-256
    Signature algorithm: SHA256withECDSA, 256-bit key

jar verified.

Warning: 
This jar contains entries whose signer certificate's ExtendedKeyUsage extension doesn't allow code signing.
This jar contains entries whose signer certificate will expire within six months. 
This jar contains signatures that do not include a timestamp. Without a timestamp, users may not be able to validate this jar after any of the signer certificates expire (as early as 2024-05-31).
```

**Warning**: Please verify the signer, the signer must be CN=flowerinsnow.online .

**Warning**: The following content indicates that the verification failed. If the file is downloaded from a non-this repository, please do not use it!

1. When the signer's CN is not `flowerinsnow.online`, it means that the signer is not the current author.
2. When something like `jarsigner: java.lang.SecurityException: SHA-256 digest error for online/flowerinsnow/greatscrollabletooltips/GreatScrollableTooltips.class`, it means that the jar file has been modified.
3. When something like `This jar contains entries whose certificate chain is invalid. Reason: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target` or `This jar contains entries whose signer certificate is self-signed.`, it indicates that the copy was signed by an unknown signer.
4. When something like `jar is unsigned.`. It means that the copy is not signed.

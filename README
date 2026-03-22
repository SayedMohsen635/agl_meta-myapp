# meta-myapp

> A custom Yocto/BitBake layer that packages the [AGL Flutter App](https://github.com/SayedMohsen635/agl_flutter_app) for the **Automotive Grade Linux (AGL)** platform — built as part of the **Google Summer of Code 2026** entry task under the [Linux Foundation](https://www.linuxfoundation.org/).

---

## Overview

This layer provides a BitBake recipe to build, package, and integrate the AGL Flutter demo app into an AGL Linux image. It demonstrates the full Yocto workflow for embedding a Flutter application into an automotive-grade IVI (In-Vehicle Infotainment) platform.

The app:
- Displays the AGL version from `/etc/os-release`
- Shows the developer's name
- Button 1 → displays an image
- Button 2 → plays audio

---

## Layer Structure

```
meta-myapp/
├── conf/
│   └── layer.conf                          ← Layer configuration
└── recipes-myapp/
    └── agl-flutter-app/
        └── agl_flutter_app.bb              ← BitBake recipe
```

---

## Recipe Details

**File:** `recipes-myapp/agl-flutter-app/agl_flutter_app.bb`

```bitbake
SUMMARY = "AGL Flutter App"
DESCRIPTION = "A Flutter demo app built for GSoC 2026 AGL task."
LICENSE = "CLOSED"

PN = "agl-flutter-app"

SRC_URI = "git://github.com/SayedMohsen635/agl_flutter_app.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

do_compile[network] = "1"

inherit agl-app flutter-app

PUBSPEC_APPNAME = "agl_flutter_app"
FLUTTER_APPLICATION_INSTALL_PREFIX = "/flutter"
FLUTTER_BUILD_ARGS = "bundle -v"

AGL_APP_TEMPLATE = "agl-app-flutter"
AGL_APP_ID = "agl_flutter_app"
AGL_APP_NAME = "AGL Flutter App"
```

### What each part does

| Variable | Value | Purpose |
|---|---|---|
| `inherit flutter-app` | — | Runs `flutter pub get` + `flutter build bundle` |
| `inherit agl-app` | — | Generates systemd service + registers with AGL homescreen |
| `PUBSPEC_APPNAME` | `agl_flutter_app` | Must match `name:` in `pubspec.yaml` |
| `FLUTTER_APPLICATION_INSTALL_PREFIX` | `/flutter` | Install path on target device |
| `FLUTTER_BUILD_ARGS` | `bundle -v` | Build mode for AGL Flutter runtime |
| `AGL_APP_TEMPLATE` | `agl-app-flutter` | AGL homescreen integration template |
| `AGL_APP_ID` | `agl_flutter_app` | Unique ID used by AGL App Framework |
| `AGL_APP_NAME` | `AGL Flutter App` | Display name on AGL homescreen |
| `do_compile[network]` | `1` | Allows pub advisory check during compile |

---

## Layer Dependencies

| Layer | Collection Name | Purpose |
|---|---|---|
| `external/meta-flutter` | `flutter-layer` | Provides `flutter-app.bbclass` |
| `meta-agl/meta-app-framework` | `meta-app-framework` | Provides `agl-app.bbclass` |

---

## Prerequisites

- AGL workspace cloned from [AGL documentation](https://docs.automotivelinux.org/)
- Yocto/BitBake build environment initialized
- `meta-flutter` and `meta-app-framework` layers available

---

## Setup & Build

### 1. Clone this layer into your AGL workspace

```bash
cd /path/to/agl/master
git clone https://github.com/SayedMohsen635/agl_meta-myapp.git meta-myapp
```

### 2. Initialize the AGL build environment

```bash
source meta-agl/scripts/aglsetup.sh -m qemux86-64 agl-demo agl-flutter
```

### 3. Add the layer to `bblayers.conf`

```bash
echo 'BBLAYERS =+ "/path/to/agl/master/meta-myapp"' >> build/conf/bblayers.conf
```

### 4. Add the app to the image in `local.conf`

```bash
echo 'IMAGE_INSTALL:append = " agl-flutter-app"' >> build/conf/local.conf
```

### 5. Build the package

```bash
bitbake agl-flutter-app
```

### 6. Build the full AGL image

```bash
bitbake agl-ivi-demo-flutter
```

---

## Build Output

After a successful package build, the app is installed at:

```
/flutter/agl_flutter_app/
└── 3.38.3/
    └── release/
        ├── data/
        │   └── flutter_assets/     ← App assets (images, audio, fonts)
        └── lib/
            └── libapp.so           ← Compiled Flutter app
```

A systemd service is also generated:
```
/usr/lib/systemd/system/agl-app-flutter@agl_flutter_app.service
```

---

## Launching the App on AGL

After booting the AGL image in QEMU:

```bash
# Via AGL App Framework
afm-util start agl_flutter_app

# Or via systemd directly
systemctl start agl-app-flutter@agl_flutter_app.service
```

---

## Build Environment

| Component | Version |
|---|---|
| Machine | `qemux86-64` |
| Distro | AGL scarthgap `21.90.0` |
| BitBake | `2.8.1` |
| Flutter SDK | `3.38.3` |
| Yocto Release | scarthgap |

---

## Related Repositories

- **Flutter App:** [SayedMohsen635/agl_flutter_app](https://github.com/SayedMohsen635/agl_flutter_app)
- **Yocto Layer:** [SayedMohsen635/agl_meta-myapp](https://github.com/SayedMohsen635/agl_meta-myapp) ← this repo

---

## Acknowledgements

- [Automotive Grade Linux (AGL)](https://www.automotivelinux.org/)
- [The Linux Foundation](https://www.linuxfoundation.org/)
- [Google Summer of Code 2026](https://summerofcode.withgoogle.com/)
- [meta-flutter](https://github.com/meta-flutter/meta-flutter) — Flutter Yocto layer

-
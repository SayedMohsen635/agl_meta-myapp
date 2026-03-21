SUMMARY = "AGL Flutter Demo App"
DESCRIPTION = "A Flutter demo app for GSoC 2026 AGL task."
LICENSE = "CLOSED"
SRC_URI = "git://github.com/SayedMohsen635/agl_flutter_app.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

inherit agl-app flutter-app

PUBSPEC_APPNAME = "agl_flutter_app"
FLUTTER_APPLICATION_INSTALL_PREFIX = "/flutter"
FLUTTER_BUILD_ARGS = "bundle -v"

AGL_APP_TEMPLATE = "agl-app-flutter"
AGL_APP_ID = "agl_flutter_app"
AGL_APP_NAME = "AGL Flutter App"

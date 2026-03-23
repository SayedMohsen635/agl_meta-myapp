SUMMARY = "AGL Flutter App"
DESCRIPTION = "A Flutter demo app built for GSoC 2026 AGL task."
LICENSE = "CLOSED"

PN = "agl-flutter-app"

SRC_URI = "git://github.com/SayedMohsen635/agl_flutter_app.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit agl-app flutter-app

# This ensures Yocto finds GStreamer headers during do_compile
DEPENDS += "gstreamer1.0 gstreamer1.0-plugins-base"

# This ensures the runtime image has the necessary libraries to play audio
RDEPENDS:${PN} += "gstreamer1.0-plugins-base-playback gstreamer1.0-plugins-good-autodetect"

PUBSPEC_APPNAME = "agl_flutter_app"
FLUTTER_APPLICATION_INSTALL_PREFIX = "/usr/share/flutter"
FLUTTER_BUILD_ARGS = "bundle -v"

AGL_APP_TEMPLATE = "agl-app-flutter"
AGL_APP_ID = "agl_flutter_app"
AGL_APP_NAME = "AGL Flutter App"

do_compile[network] = "1"

SUMMARY = "Audio Player Service"
DESCRIPTION = "GStreamer-based audio playback service"
LICENSE = "CLOSED"

SRC_URI = "file://audio_service.cpp"

S = "${WORKDIR}"

DEPENDS += "gstreamer1.0 glib-2.0"

inherit pkgconfig

do_compile() {
    ${CXX} audio_service.cpp -o audio_service \
        `${PKG_CONFIG} --cflags --libs gstreamer-1.0 gio-2.0`
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 audio_service ${D}${bindir}
}

inherit gettext

SUMMARY = "Downloads the LG Watch Urbane 2 /system and /usr/include/android folders and installs them for libhybris"
LICENSE = "CLOSED"
SRC_URI = "https://dl.dropboxusercontent.com/s/ibijpyuz2tpiyk4/system-MFD18L.tar.gz"
SRC_URI[md5sum] = "239192b6744e6ecd57848e7576407d49"
SRC_URI[sha256sum] = "c610fdd684049046890595b0084c9e0c2f9e24a43df5f7ad778fce810a096715"
PV = "marshmallow"

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"
COMPATIBLE_MACHINE = "nemo"
INSANE_SKIP_${PN} = "already-stripped"
S = "${WORKDIR}"
B = "${S}"

PROVIDES += "virtual/android-system-image"
PROVIDES += "virtual/android-headers"

do_install() {
    install -d ${D}/system/
    cp -r system/* ${D}/system/

    install -d ${D}/usr/
    cp -r usr/* ${D}/usr/

    install -d ${D}${includedir}/android
    cp -r include/* ${D}${includedir}/android/

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${D}${includedir}/android/android-headers.pc ${D}${libdir}/pkgconfig
    rm ${D}${includedir}/android/android-headers.pc
    cd ${D}
    ln -s system/vendor vendor

    mkdir etc/
    ln -s /system/etc/firmware etc/firmware
}

# FIXME: QA Issue: Architecture did not match (40 to 164) on /work/nemo-oe-linux-gnueabi/android/lollipop-r0/packages-split/android-system/system/vendor/firmware/adsp.b00 [arch]
do_package_qa() {
}

PACKAGES =+ "android-system android-headers"
FILES_android-system = "/system /vendor /etc /usr"
FILES_android-headers = "${libdir}/pkgconfig ${includedir}/android"

require recipes-kernel/linux/linux.inc
inherit gettext

SECTION = "kernel"
SUMMARY = "Android kernel for the LG Watch Urbane 2 LTE"
HOMEPAGE = "https://android.googlesource.com/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "nemo"
#;striplevel=1
SRC_URI = "git://android.googlesource.com/kernel/msm;branch=android-msm-nemo-3.10-marshmallow-mr1-wear-release;protocol=https \
    file://0001-static-inline-in-ARM-ftrace.h.patch \
    file://0006-ARM-uaccess-remove-put_user-code-duplication.patch \
    file://0001-Revert-Enable-Nitrous-BT-power-management-driver-for.patch \
    file://0002-Backport-mainline-4.1-Bluetooth-subsystem.patch \
    file://0003-bluesleep-Use-kernel-s-HCI-events-instead-of-proc-bl.patch \
    file://defconfig \
    file://img_info "
SRCREV = "504f3357f3ef296bf5ccbfe05df2025fa41eb354"
LINUX_VERSION ?= "3.10"
PV = "${LINUX_VERSION}+marshmallow"
S = "${WORKDIR}/git"
B = "${S}"

do_install_append() {
    rm -rf ${D}/usr/src/usr/
}

BOOT_PARTITION = "/dev/mmcblk0p15"

inherit mkboot old-kernel-gcc-hdrs

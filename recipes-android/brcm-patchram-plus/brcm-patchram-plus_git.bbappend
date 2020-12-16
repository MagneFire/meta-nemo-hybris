FILESEXTRAPATHS_prepend_nemo := "${THISDIR}/brcm-patchram-plus:"
SRC_URI_append_nemo = " file://patchram.service "
CFLAGS_append_nemo = " -DLPM_NEMO"

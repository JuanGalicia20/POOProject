package com.project.app.aplicacionmovil

import java.util.*

class Pendientes {
    var nombrePendiente: String? = null
    var horaPendiente: Date? = null
    var descripcionPendiente: String? = null

    constructor(nombrePendiente: String?, horaPendiente: Date?, descripcionPendiente: String?) {
        this.nombrePendiente = nombrePendiente
        this.horaPendiente = horaPendiente
        this.descripcionPendiente = descripcionPendiente
    }

    constructor() {}
}
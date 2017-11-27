package com.yolasite.hardtapgames.smack.Model

/**
 * Created by ronnie on 27/11/17.
 */
class Channel (val name: String, val description: String, val id: String) {
    override fun toString(): String {
        return "#$name"
    }

}
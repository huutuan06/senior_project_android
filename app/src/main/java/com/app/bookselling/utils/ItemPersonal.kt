package com.app.bookselling.utils

class ItemPersonal {
    lateinit var imgProfile: String
    lateinit var nameProfile: String
    lateinit var txtManage: String
    lateinit var imgManage: String

    constructor(imgProfile: String, nameProfile: String, imgManage: String, txtManage: String) {
        this.imgProfile = imgProfile
        this.nameProfile = nameProfile
        this.txtManage = txtManage
        this.imgManage = imgManage
    }
}
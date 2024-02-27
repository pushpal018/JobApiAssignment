package com.api.search.data.network.api_response

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : Data?    = Data(),
    @SerializedName("status"  ) var status  : Boolean? = null
)

data class User (

    @SerializedName("id"                ) var id               : Int?    = null,
    @SerializedName("name"              ) var name             : String? = null,
    @SerializedName("username"          ) var username         : String? = null,
    @SerializedName("refer_code"        ) var referCode        : String? = null,
    @SerializedName("refer_by"          ) var referBy          : String? = null,
    @SerializedName("email"             ) var email            : String? = null,
    @SerializedName("phone"             ) var phone            : String? = null,
    @SerializedName("image"             ) var image            : String? = null,
    @SerializedName("fathers_name"      ) var fathersName      : String? = null,
    @SerializedName("mothers_name"      ) var mothersName      : String? = null,
    @SerializedName("present_address"   ) var presentAddress   : String? = null,
    @SerializedName("parmanent_address" ) var parmanentAddress : String? = null,
    @SerializedName("date_of_birth"     ) var dateOfBirth      : String? = null,
    @SerializedName("nationality"       ) var nationality      : String? = null,
    @SerializedName("religion"          ) var religion         : String? = null,
    @SerializedName("nid"               ) var nid              : String? = null,
    @SerializedName("birth_certificate" ) var birthCertificate : String? = null,
    @SerializedName("blood_group"       ) var bloodGroup       : String? = null,
    @SerializedName("gender"            ) var gender           : String? = null,
    @SerializedName("edu_qualification" ) var eduQualification : String? = null,
    @SerializedName("experience"        ) var experience       : String? = null,
    @SerializedName("staff_id"          ) var staffId          : String? = null,
    @SerializedName("staff_type"        ) var staffType        : String? = null,
    @SerializedName("type"              ) var type             : Int?    = null,
    @SerializedName("department_id"     ) var departmentId     : String? = null,
    @SerializedName("designation_id"    ) var designationId    : String? = null,
    @SerializedName("office_zone"       ) var officeZone       : String? = null,
    @SerializedName("joining_date"      ) var joiningDate      : String? = null,
    @SerializedName("discharge_date"    ) var dischargeDate    : String? = null,
    @SerializedName("machine_id"        ) var machineId        : String? = null,
    @SerializedName("description"       ) var description      : String? = null,
    @SerializedName("marital_status"    ) var maritalStatus    : String? = null,
    @SerializedName("salary"            ) var salary           : String? = null,
    @SerializedName("show_password"     ) var showPassword     : String? = null,
    @SerializedName("email_verified_at" ) var emailVerifiedAt  : String? = null,
    @SerializedName("created_by"        ) var createdBy        : String? = null,
    @SerializedName("updated_by"        ) var updatedBy        : String? = null,
    @SerializedName("deleted_by"        ) var deletedBy        : String? = null,
    @SerializedName("status"            ) var status           : Int?    = null,
    @SerializedName("menu"              ) var menu             : Int?    = null,
    @SerializedName("deleted_at"        ) var deletedAt        : String? = null,
    @SerializedName("created_at"        ) var createdAt        : String? = null,
    @SerializedName("updated_at"        ) var updatedAt        : String? = null

)

data class Data (

    @SerializedName("user"         ) var user        : User?   = User(),
    @SerializedName("access_token" ) var accessToken : String? = null

)

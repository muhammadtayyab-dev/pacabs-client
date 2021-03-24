package com.techlogix.pacaps.models.cabAndDriverInformationModels

data class CabDetailsInformationModel(var cabType: String,
                                      var cabDesc: String,
                                      var cabETArivalTime: String,
                                      var cabFare: String,
                                      var isSelected: Boolean)
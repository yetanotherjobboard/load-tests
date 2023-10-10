var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "17000",
        "ok": "16939",
        "ko": "61"
    },
    "minResponseTime": {
        "total": "180",
        "ok": "193",
        "ko": "180"
    },
    "maxResponseTime": {
        "total": "30267",
        "ok": "30215",
        "ko": "30267"
    },
    "meanResponseTime": {
        "total": "11171",
        "ok": "11110",
        "ko": "28223"
    },
    "standardDeviation": {
        "total": "6811",
        "ok": "6731",
        "ko": "7427"
    },
    "percentiles1": {
        "total": "13468",
        "ok": "13456",
        "ko": "30182"
    },
    "percentiles2": {
        "total": "15793",
        "ok": "15772",
        "ko": "30191"
    },
    "percentiles3": {
        "total": "19392",
        "ok": "19251",
        "ko": "30235"
    },
    "percentiles4": {
        "total": "24272",
        "ok": "23038",
        "ko": "30264"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 3795,
    "percentage": 22
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 376,
    "percentage": 2
},
    "group3": {
    "name": "t >= 5000 ms",
    "htmlName": "t >= 5000 ms",
    "count": 12768,
    "percentage": 75
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 61,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "6.703",
        "ok": "6.679",
        "ko": "0.024"
    }
},
contents: {
"req_search-06a94": {
        type: "REQUEST",
        name: "search",
path: "search",
pathFormatted: "req_search-06a94",
stats: {
    "name": "search",
    "numberOfRequests": {
        "total": "17000",
        "ok": "16939",
        "ko": "61"
    },
    "minResponseTime": {
        "total": "180",
        "ok": "193",
        "ko": "180"
    },
    "maxResponseTime": {
        "total": "30267",
        "ok": "30215",
        "ko": "30267"
    },
    "meanResponseTime": {
        "total": "11171",
        "ok": "11110",
        "ko": "28223"
    },
    "standardDeviation": {
        "total": "6811",
        "ok": "6731",
        "ko": "7427"
    },
    "percentiles1": {
        "total": "13468",
        "ok": "13456",
        "ko": "30182"
    },
    "percentiles2": {
        "total": "15793",
        "ok": "15772",
        "ko": "30191"
    },
    "percentiles3": {
        "total": "19392",
        "ok": "19251",
        "ko": "30235"
    },
    "percentiles4": {
        "total": "24272",
        "ok": "23038",
        "ko": "30264"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 3795,
    "percentage": 22
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 376,
    "percentage": 2
},
    "group3": {
    "name": "t >= 5000 ms",
    "htmlName": "t >= 5000 ms",
    "count": 12768,
    "percentage": 75
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 61,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "6.703",
        "ok": "6.679",
        "ko": "0.024"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
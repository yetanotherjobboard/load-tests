var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "2000",
        "ok": "2000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "176",
        "ok": "176",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "3157",
        "ok": "3157",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "298",
        "ok": "298",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "250",
        "ok": "250",
        "ko": "-"
    },
    "percentiles1": {
        "total": "235",
        "ok": "235",
        "ko": "-"
    },
    "percentiles2": {
        "total": "306",
        "ok": "306",
        "ko": "-"
    },
    "percentiles3": {
        "total": "476",
        "ok": "476",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1761",
        "ok": "1761",
        "ko": "-"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 1959,
    "percentage": 98
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 41,
    "percentage": 2
},
    "group3": {
    "name": "t >= 5000 ms",
    "htmlName": "t >= 5000 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "9.756",
        "ok": "9.756",
        "ko": "-"
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
        "total": "2000",
        "ok": "2000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "176",
        "ok": "176",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "3157",
        "ok": "3157",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "298",
        "ok": "298",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "250",
        "ok": "250",
        "ko": "-"
    },
    "percentiles1": {
        "total": "235",
        "ok": "235",
        "ko": "-"
    },
    "percentiles2": {
        "total": "306",
        "ok": "306",
        "ko": "-"
    },
    "percentiles3": {
        "total": "476",
        "ok": "476",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1761",
        "ok": "1761",
        "ko": "-"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 1959,
    "percentage": 98
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 41,
    "percentage": 2
},
    "group3": {
    "name": "t >= 5000 ms",
    "htmlName": "t >= 5000 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "9.756",
        "ok": "9.756",
        "ko": "-"
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

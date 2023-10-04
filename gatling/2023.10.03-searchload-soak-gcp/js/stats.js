var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "4375",
        "ok": "4375",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "196",
        "ok": "196",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "18786",
        "ok": "18786",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1889",
        "ok": "1889",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2842",
        "ok": "2842",
        "ko": "-"
    },
    "percentiles1": {
        "total": "250",
        "ok": "250",
        "ko": "-"
    },
    "percentiles2": {
        "total": "2829",
        "ok": "2829",
        "ko": "-"
    },
    "percentiles3": {
        "total": "7903",
        "ok": "7903",
        "ko": "-"
    },
    "percentiles4": {
        "total": "12599",
        "ok": "12599",
        "ko": "-"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 2782,
    "percentage": 64
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 1021,
    "percentage": 23
},
    "group3": {
    "name": "t >= 5000 ms",
    "htmlName": "t >= 5000 ms",
    "count": 572,
    "percentage": 13
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "5.208",
        "ok": "5.208",
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
        "total": "4375",
        "ok": "4375",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "196",
        "ok": "196",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "18786",
        "ok": "18786",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1889",
        "ok": "1889",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2842",
        "ok": "2842",
        "ko": "-"
    },
    "percentiles1": {
        "total": "250",
        "ok": "250",
        "ko": "-"
    },
    "percentiles2": {
        "total": "2829",
        "ok": "2829",
        "ko": "-"
    },
    "percentiles3": {
        "total": "7903",
        "ok": "7903",
        "ko": "-"
    },
    "percentiles4": {
        "total": "12599",
        "ok": "12599",
        "ko": "-"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 2782,
    "percentage": 64
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 1021,
    "percentage": 23
},
    "group3": {
    "name": "t >= 5000 ms",
    "htmlName": "t >= 5000 ms",
    "count": 572,
    "percentage": 13
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "5.208",
        "ok": "5.208",
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

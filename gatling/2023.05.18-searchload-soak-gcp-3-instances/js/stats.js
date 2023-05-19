var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "17000",
        "ok": "16224",
        "ko": "776"
    },
    "minResponseTime": {
        "total": "183",
        "ok": "195",
        "ko": "183"
    },
    "maxResponseTime": {
        "total": "60008",
        "ok": "50325",
        "ko": "60008"
    },
    "meanResponseTime": {
        "total": "9982",
        "ok": "8851",
        "ko": "33610"
    },
    "standardDeviation": {
        "total": "10424",
        "ok": "9019",
        "ko": "9739"
    },
    "percentiles1": {
        "total": "7667",
        "ok": "6519",
        "ko": "30182"
    },
    "percentiles2": {
        "total": "17397",
        "ok": "16023",
        "ko": "30191"
    },
    "percentiles3": {
        "total": "29654",
        "ok": "25691",
        "ko": "60001"
    },
    "percentiles4": {
        "total": "30206",
        "ok": "28970",
        "ko": "60002"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 6268,
    "percentage": 37
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 1335,
    "percentage": 8
},
    "group3": {
    "name": "t >= 5000 ms",
    "htmlName": "t >= 5000 ms",
    "count": 8621,
    "percentage": 51
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 776,
    "percentage": 5
},
    "meanNumberOfRequestsPerSecond": {
        "total": "7.284",
        "ok": "6.951",
        "ko": "0.332"
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
        "ok": "16224",
        "ko": "776"
    },
    "minResponseTime": {
        "total": "183",
        "ok": "195",
        "ko": "183"
    },
    "maxResponseTime": {
        "total": "60008",
        "ok": "50325",
        "ko": "60008"
    },
    "meanResponseTime": {
        "total": "9982",
        "ok": "8851",
        "ko": "33610"
    },
    "standardDeviation": {
        "total": "10424",
        "ok": "9019",
        "ko": "9739"
    },
    "percentiles1": {
        "total": "7667",
        "ok": "6519",
        "ko": "30182"
    },
    "percentiles2": {
        "total": "17397",
        "ok": "16023",
        "ko": "30191"
    },
    "percentiles3": {
        "total": "29654",
        "ok": "25691",
        "ko": "60001"
    },
    "percentiles4": {
        "total": "30206",
        "ok": "28971",
        "ko": "60002"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 6268,
    "percentage": 37
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 1335,
    "percentage": 8
},
    "group3": {
    "name": "t >= 5000 ms",
    "htmlName": "t >= 5000 ms",
    "count": 8621,
    "percentage": 51
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 776,
    "percentage": 5
},
    "meanNumberOfRequestsPerSecond": {
        "total": "7.284",
        "ok": "6.951",
        "ko": "0.332"
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

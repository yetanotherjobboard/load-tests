var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "50000",
        "ok": "50000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "177",
        "ok": "177",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "58708",
        "ok": "58708",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1559",
        "ok": "1559",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2907",
        "ok": "2907",
        "ko": "-"
    },
    "percentiles1": {
        "total": "562",
        "ok": "562",
        "ko": "-"
    },
    "percentiles2": {
        "total": "879",
        "ok": "879",
        "ko": "-"
    },
    "percentiles3": {
        "total": "7453",
        "ok": "7453",
        "ko": "-"
    },
    "percentiles4": {
        "total": "14960",
        "ok": "14960",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 34848,
    "percentage": 70
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 5095,
    "percentage": 10
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 10057,
    "percentage": 20
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "52.356",
        "ok": "52.356",
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
        "total": "50000",
        "ok": "50000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "177",
        "ok": "177",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "58708",
        "ok": "58708",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1559",
        "ok": "1559",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2907",
        "ok": "2907",
        "ko": "-"
    },
    "percentiles1": {
        "total": "562",
        "ok": "562",
        "ko": "-"
    },
    "percentiles2": {
        "total": "879",
        "ok": "879",
        "ko": "-"
    },
    "percentiles3": {
        "total": "7453",
        "ok": "7453",
        "ko": "-"
    },
    "percentiles4": {
        "total": "14960",
        "ok": "14960",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 34848,
    "percentage": 70
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 5095,
    "percentage": 10
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 10057,
    "percentage": 20
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "52.356",
        "ok": "52.356",
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

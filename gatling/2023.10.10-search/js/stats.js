var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "18875",
        "ok": "18871",
        "ko": "4"
    },
    "minResponseTime": {
        "total": "54",
        "ok": "54",
        "ko": "10216"
    },
    "maxResponseTime": {
        "total": "21506",
        "ok": "21506",
        "ko": "10279"
    },
    "meanResponseTime": {
        "total": "291",
        "ok": "289",
        "ko": "10254"
    },
    "standardDeviation": {
        "total": "1042",
        "ok": "1032",
        "ko": "27"
    },
    "percentiles1": {
        "total": "94",
        "ok": "94",
        "ko": "10260"
    },
    "percentiles2": {
        "total": "169",
        "ok": "169",
        "ko": "10279"
    },
    "percentiles3": {
        "total": "1007",
        "ok": "1002",
        "ko": "10279"
    },
    "percentiles4": {
        "total": "3244",
        "ok": "3218",
        "ko": "10279"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 17923,
    "percentage": 95
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 848,
    "percentage": 4
},
    "group3": {
    "name": "t >= 5000 ms",
    "htmlName": "t >= 5000 ms",
    "count": 100,
    "percentage": 1
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 4,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "31.723",
        "ok": "31.716",
        "ko": "0.007"
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
        "total": "18875",
        "ok": "18871",
        "ko": "4"
    },
    "minResponseTime": {
        "total": "54",
        "ok": "54",
        "ko": "10216"
    },
    "maxResponseTime": {
        "total": "21506",
        "ok": "21506",
        "ko": "10279"
    },
    "meanResponseTime": {
        "total": "291",
        "ok": "289",
        "ko": "10254"
    },
    "standardDeviation": {
        "total": "1042",
        "ok": "1032",
        "ko": "27"
    },
    "percentiles1": {
        "total": "94",
        "ok": "94",
        "ko": "10260"
    },
    "percentiles2": {
        "total": "169",
        "ok": "169",
        "ko": "10279"
    },
    "percentiles3": {
        "total": "1007",
        "ok": "1002",
        "ko": "10279"
    },
    "percentiles4": {
        "total": "3244",
        "ok": "3218",
        "ko": "10279"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 17923,
    "percentage": 95
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 848,
    "percentage": 4
},
    "group3": {
    "name": "t >= 5000 ms",
    "htmlName": "t >= 5000 ms",
    "count": 100,
    "percentage": 1
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 4,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "31.723",
        "ok": "31.716",
        "ko": "0.007"
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

var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "17000",
        "ok": "17000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "172",
        "ok": "172",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2024",
        "ok": "2024",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "243",
        "ok": "243",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "91",
        "ok": "91",
        "ko": "-"
    },
    "percentiles1": {
        "total": "210",
        "ok": "210",
        "ko": "-"
    },
    "percentiles2": {
        "total": "256",
        "ok": "256",
        "ko": "-"
    },
    "percentiles3": {
        "total": "429",
        "ok": "429",
        "ko": "-"
    },
    "percentiles4": {
        "total": "558",
        "ok": "558",
        "ko": "-"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 16984,
    "percentage": 100
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 16,
    "percentage": 0
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
        "total": "29.982",
        "ok": "29.982",
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
        "total": "17000",
        "ok": "17000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "172",
        "ok": "172",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2024",
        "ok": "2024",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "243",
        "ok": "243",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "91",
        "ok": "91",
        "ko": "-"
    },
    "percentiles1": {
        "total": "210",
        "ok": "210",
        "ko": "-"
    },
    "percentiles2": {
        "total": "256",
        "ok": "256",
        "ko": "-"
    },
    "percentiles3": {
        "total": "429",
        "ok": "429",
        "ko": "-"
    },
    "percentiles4": {
        "total": "558",
        "ok": "558",
        "ko": "-"
    },
    "group1": {
    "name": "t < 1000 ms",
    "htmlName": "t < 1000 ms",
    "count": 16984,
    "percentage": 100
},
    "group2": {
    "name": "1000 ms <= t < 5000 ms",
    "htmlName": "t >= 1000 ms <br> t < 5000 ms",
    "count": 16,
    "percentage": 0
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
        "total": "29.982",
        "ok": "29.982",
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

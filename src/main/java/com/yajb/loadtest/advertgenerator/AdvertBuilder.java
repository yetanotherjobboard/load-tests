package com.yajb.loadtest.advertgenerator;

import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.Companies.acme;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.Companies.donkeyballs;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.Companies.hookerfurniture;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.Companies.januszex;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.Companies.tequilamockingbird;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.benefits;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.benefits_scope;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.hourlyRateFactor;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.names;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.officeLocations;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.responsibilities_noun;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.responsibilities_verb;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.skills_adj;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.videos;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Randomizer.pickOne;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Randomizer.rnd;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Randomizer.withProbability;
import static java.util.stream.IntStream.rangeClosed;

import com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.Companies;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import yajb.com.client.account.model.Company;
import yajb.com.client.account.model.ContractType;
import yajb.com.client.account.model.GeoLocation;
import yajb.com.client.account.model.JobAdvertDetails;
import yajb.com.client.account.model.JobAdvertDraft;
import yajb.com.client.account.model.SalaryBands;
import yajb.com.client.account.model.SalaryPeriod;
import yajb.com.client.account.model.Size;
import yajb.com.client.account.model.Video;
import yajb.com.client.account.model.WorkLocation;
import yajb.com.client.config.model.StaticConfig;

@RequiredArgsConstructor
class AdvertBuilder {

  final StaticConfig staticConfig;
  Company company;
  List<String> description = new ArrayList<>();
  SalaryBands salaryBands;
  Video video;

  static JobAdvertDraft randomAd(StaticConfig staticConfig) {
    return new AdvertBuilder(staticConfig)
        .randomCompany()
        .randomHeading()
        .randomResponsibilities()
        .randomSkills()
        .randomBenefits()
        .randomSalaryBands()
        .randomVideo()
        .build();
  }

  AdvertBuilder randomCompany() {
    this.company = pickOne(Companies.ALL).industry(pickOne(staticConfig.getIndustries()));
    return this;
  }
  AdvertBuilder randomVideo() {
    withProbability(99, () -> video = pickOne(videos));
    return this;
  }
  AdvertBuilder randomHeading() {
    withProbability(
        5,
        () -> description.add("(%s, please check and proofread before posting)".formatted(pickOne(names))));
    description.add(pickOne(Data.headings_first_line).formatted(company.getName()));
    description.add(pickOne(Data.headings_second_line));
    return this;
  }

  AdvertBuilder randomResponsibilities() {
    description.add("\n");
    description.add("Responsibilities:");
    description.addAll(rangeClosed(1, 5)
        .mapToObj(i -> String.join(
            " ",
            "  -",
            pickOne(responsibilities_verb),
            pickOne(responsibilities_noun)))
        .toList());
    return this;
  }

  AdvertBuilder randomSkills() {
    description.add("\n");
    description.add("Required skills:");
    description.addAll(rangeClosed(1, 5)
        .mapToObj(i -> String.join(
            " ",
            "  -",
            pickOne(skills_adj),
            pickOne(responsibilities_verb),
            pickOne(responsibilities_noun)))
        .toList());
    return this;
  }

  AdvertBuilder randomBenefits() {
    description.add("\n");
    description.add("What we offer:");
    description.addAll(rangeClosed(1, 3)
        .mapToObj(i -> String.join(
            " ",
            "  -",
            pickOne(benefits),
            pickOne(benefits_scope)))
        .toList());
    return this;
  }

  private AdvertBuilder randomSalaryBands() {
    withProbability(75, () -> {
      int minHourlyRate = (int) rnd(50, 45);
      int maxHourlyRate = (int) rnd(200, 100);
      salaryBands = new SalaryBands()
          .currency(pickOne(Data.currencies))
          .period(pickOne(List.of(SalaryPeriod.values())))
          .min(minHourlyRate * hourlyRateFactor.get(pickOne(List.of(SalaryPeriod.values()))))
          .max(maxHourlyRate * hourlyRateFactor.get(pickOne(List.of(SalaryPeriod.values()))));
    });
    return this;
  }

  JobAdvertDraft build() {
    var details = new JobAdvertDetails()
        .company(company)
        .roleType(pickOne(staticConfig.getRoleTypes()))
        .seniorityLevel(pickOne(staticConfig.getSeniorityLevels()))
        .title(String.join(" ", pickOne(Data.jobLevels), pickOne(Data.jobs), pickOne(Data.jobTypes)))
        .salaryBands(salaryBands)
        .contractTypes(Set.of(pickOne(List.of(ContractType.values()))))
        .workLocation(pickOne(List.of(WorkLocation.values())))
        .videoLocation(video)
        .officeLocation(pickOne(officeLocations.get(company)))
        .contactEmail(null);

    return new JobAdvertDraft()
        .details(details)
        .description(String.join("\n", description))
        .applyLink(company.getWebsiteUrl() + "/jobs");
  }

  interface Data {

    Map<SalaryPeriod, Integer> hourlyRateFactor =
        Map.of(
            SalaryPeriod.HOURLY, 1,
            SalaryPeriod.DAILY, 8,
            SalaryPeriod.WEEKLY, 8 * 5,
            SalaryPeriod.BIWEEKLY, 2 * 8 * 5,
            SalaryPeriod.MONTHLY, 8 * 21,
            SalaryPeriod.ANNUALLY, 8 * 250);
    List<String> names =
        List.of(
            "Tony", "Claudia", "Ben", "Tim", "Ron", "Betty", "Mike", "Frankie", "Lucy", "Glenn",
            "Rene", "Chris", "Leslie", "Maude", "Linda", "Rick", "Nick", "Dick", "Benny", "Luisa",
            "Mandy", "Johanna");
    List<String> headings_first_line =
        List.of(
            "%s is hiring!",
            "Join %s!",
            "Opportunity exclusively at %s!",
            "New position with %s!",
            "We are hiring again at %s!",
            "Apply to %s today!",
            "Don't let others beat you to %s!",
            "%s wants you!",
            "%s needs you!",
            "Would you like to work for %s?",
            "Have you heard of %s?");
    List<String> headings_second_line =
        List.of(
            "We need the best of the best, so come and join us.",
            "Tired of your boss? Here's your chance to give him a finger.",
            "This role might be something just for you.",
            "Fantastic opportunity to boost your career.",
            "A chance for a better role, better money, better life.",
            "You cannot pass on this opportunity.",
            "Let's make it happen.",
            "Fed up with your current mundane job? You can change it today.",
            "Just go for it.",
            "This might be the job you were waiting for.",
            "Is it time to change?");

    List<String> jobLevels =
        List.of(
            "Associate",
            "Apprentice",
            "Junior",
            "Senior",
            "Principal",
            "Expert",
            "Sr. Principal",
            "Lead",
            "Staff",
            "Chief",
            "Distinguished");
    List<String> jobs =
        List.of(
            "Software Engineering",
            "Civil Engineering",
            "Mechanical Engineering",
            "Web Design",
            "IT",
            "Networking",
            "Attorney",
            "Carpentry",
            "Welding",
            "Skin Care",
            "Executive",
            "Office",
            "Project",
            "Product",
            "HR",
            "Dog Grooming",
            "Nursing",
            "Sales",
            "Marketing",
            "Outsourcing",
            "Plumbing",
            "Heavy Equipment",
            "Wood Construction",
            "Concrete Construction",
            "Accounting",
            "Customer Service",
            "Business Development",
            "Recruitment",
            "Payroll",
            "Quality Assurance",
            "Ethical Hacking");
    List<String> jobTypes =
        List.of(
            "Assistant",
            "Specialist",
            "Coordinator",
            "Consultant",
            "Trainer",
            "Analyst",
            "Operator",
            "Installer",
            "Partner",
            "Manager",
            "Director",
            "Country Head",
            "Vice President");
    List<String> responsibilities_verb =
        List.of(
            "managing",
            "planning",
            "buying",
            "designing",
            "architecting",
            "making",
            "filling out",
            "handling",
            "preparing",
            "running",
            "attending",
            "contributing to",
            "ensuring",
            "providing",
            "procuring",
            "owning",
            "crunching through",
            "researching",
            "selecting");
    List<String> responsibilities_noun =
        List.of(
            "budget",
            "schedules",
            "progress",
            "subordinates",
            "supplies",
            "roadmaps",
            "solutions",
            "coffee",
            "papers",
            "weekly reports",
            "daily standups",
            "monthly reviews",
            "overall success",
            "compliance",
            "valuable insights",
            "necessary means",
            "results",
            "big data",
            "applicable technologies",
            "vendors",
            "investor relationship");
    List<String> skills_adj =
        List.of(
            "expert at",
            "experienced with",
            "fluency in",
            "strong suited at",
            "familiarity with",
            "working knowledge of",
            "proven track of record with",
            "adept knowledge of",
            "deep knowledge of",
            "good understanding of",
            "outstanding expertise in",
            "hands on with",
            "acquainted with",
            "well versed in",
            "recognized authority of",
            "10+ years of experience in the field of");
    List<String> benefits =
        List.of(
            "Fruity Thursdays",
            "car allowance",
            "business phone",
            "Skoda Fabia",
            "Multisport",
            "MacBook Pro",
            "Thai Massage",
            "Group insurance",
            "Medical package",
            "Fantastic work atmosphere",
            "Young and motivated team",
            "Dynamic environment");
    List<String> benefits_scope =
        List.of(
            "every Monday",
            "every Wednesday",
            "every Friday",
            "weekly",
            "for whole family",
            "for you and your spouse",
            "for Employee Of The Week",
            "with all expenses covered",
            "(until depleted)",
            "(guaranteed through first year of employment)",
            "after probation period",
            "with unlimited mileage",
            "for top performers");
    List<String> currencies = List.of("EUR", "USD", "PLN", "CHF", "GBP");

    interface Companies {
      Company januszex = new Company()
          .name("JanuszeX")
          .websiteUrl("http://januszex.pl")
          .logoUrl(null)
          .companySize(Size.SMALL);

      Company donkeyballs = new Company()
          .name("Donkey Balls")
          .websiteUrl("http://donkeyballs.org")
          .logoUrl("https://cdn.shopify.com/s/files/1/2373/3959/files/DB_rev_logo_Yel_Blk_010311_180x.jpg")
          .companySize(Size.MEDIUM);

      Company acme = new Company()
          .name("ACME")
          .websiteUrl("http://acme.org")
          .logoUrl("https://1000logos.net/wp-content/uploads/2021/04/ACME-logo-768x432.png")
          .companySize(Size.LARGE);

      Company hookerfurniture = new Company()
          .name("Hooker Furniture")
          .websiteUrl("http://hookers.com")
          .logoUrl("https://www.greenfront.com/wp-content/uploads/2017/02/Hooker.jpg")
          .companySize(Size.LARGE);

      Company tequilamockingbird = new Company()
          .name("Tequila Mockingbird")
          .websiteUrl("http://tmb.com")
          .logoUrl("https://wiki.godvillegame.com/images/3/3b/Tequila_Mockingbird.jpeg")
          .companySize(Size.BIG);

      List<Company> ALL = List.of(januszex, donkeyballs, acme, hookerfurniture, tequilamockingbird);
    }

    Map<Company, List<GeoLocation>> officeLocations = Map.of(
        januszex, List.of(
            geo("Kozia Wólka", 53.9205661,19.8517622),
            geo("Bełchatów", 51.39391653438096, 19.370107445515238),
            geo("Kalisz", 51.7830404304157, 18.095792548165456),
            geo("Gorlitz", 51.15247718476542, 14.969228768391387)
        ),
        donkeyballs, List.of(
            geo("Prague", 50.09040047969781, 14.425790045783868),
            geo("Denver", 39.720194475155814, -104.99939932989919),
            geo("Valencia", 39.55820454526369, -0.4291489098831155)
        ),
        acme, List.of(
            geo("Malaga", 36.80471606148292, -4.418471602023769),
            geo("Wyskok", 51.15975112819496, 15.935364447956925),
            geo("Madrid", 40.436454742481025, -3.7221536792462353),
            geo("Amsterdam", 52.435413855258865, 4.925599615344822),
            geo("Bielefeld", 52.09744291561177, 8.495229305219524),
            geo("Kosice", 48.77551177998211, 21.245050070562215),
            geo("Szekesfehervar", 47.255159670897655, 18.371738206501906),
            geo("Kielce", 50.91902869921697, 20.632786200150406),
            geo("London", 51.57321203387039, -0.1487170082714032)
        ),
        hookerfurniture, List.of(
            geo("Vienna", 48.293940339403306, 16.44664397099485),
            geo("Katowice", 50.29561167590756, 18.723905313561158),
            geo("Genoa", 44.53257762827692, 8.989729392329334),
            geo("Zagreb", 45.91627326757985, 16.022448304615587)
        ),
        tequilamockingbird, List.of(
            geo("San Marino", 43.956812725361274, 12.439110589153513),
            geo("Bilbao", 43.33483037102203, -2.910077176843034),
            geo("Zurich", 47.477704080326795, 8.566581034414087),
            geo("Antwerp", 51.220391216250654, 4.793471511889504),
            geo("Zielona Góra", 52.00621756085301, 15.578418203957737),
            geo("Białystok", 53.21941160053914, 22.99068055426144),
            geo("Montpellier", 43.715247726932375, 3.9328698098610837)
        )
    );

    private static GeoLocation geo(String address, double latitude, double longitude) {
      return new GeoLocation().address(address).lat(latitude).lon(longitude);
    }

    enum VideoType {
      YouTube, Vimeo
    }
    List<Video> videos = List.of(
        video("685905913", VideoType.Vimeo),
        video("69586647", VideoType.Vimeo),
        video("649126989", VideoType.Vimeo),
        video("36226517", VideoType.Vimeo),
        video("519942773", VideoType.Vimeo),
        video("RyVCnfWp3Dk", VideoType.YouTube),
        video("S5rvJOcff_I", VideoType.YouTube),
        video("FQYTTMA90yg", VideoType.YouTube),
        video("Tj3IziSCsqo", VideoType.YouTube),
        video("GBmHv84NM5o", VideoType.YouTube)
    );

    static Video video(String videoId, VideoType type) {
      return new Video()
          .url(embeddedVideoUrl(type, videoId))
          .thumbnailUrl(videoThumbnailUrl(type, videoId));
    }

    static String embeddedVideoUrl(VideoType type, String videoId) {
      return switch (type) {
        case YouTube -> "https://www.youtube.com/embed/%s".formatted(videoId);
        case Vimeo -> "https://player.vimeo.com/video/%s".formatted(videoId);
      };
    }

    static String videoThumbnailUrl(VideoType type, String videoId) {
      return switch (type) {
        case YouTube -> "https://img.youtube.com/vi/%s/default.jpg".formatted(videoId);
        case Vimeo -> "https://vumbnail.com/%s_small.jpg".formatted(videoId);
      };
    }
  }

  interface Randomizer {
    Random random = new Random();

    static long rnd(long mid, long plusMinus) {
      return random.nextLong(mid - plusMinus, mid + plusMinus + 1);
    }
    static <T> T pickOne(List<T> ads) {
      return ads.get(random.nextInt(ads.size()));
    }

    static void withProbability(int percent, Runnable codeBlock) {
      if (percent > random.nextInt(1, 100)) codeBlock.run();
    }
  }
}

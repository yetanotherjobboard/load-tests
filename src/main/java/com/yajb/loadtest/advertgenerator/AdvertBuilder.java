package com.yajb.loadtest.advertgenerator;

import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.benefits;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.benefits_scope;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.hourlyRateFactor;
import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Data.names;
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
        .officeLocation(null) //todo pickOne
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

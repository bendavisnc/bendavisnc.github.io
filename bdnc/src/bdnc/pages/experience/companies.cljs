(ns bdnc.pages.experience.companies
  (:require
   [bdnc.pages.experience.svgs :as svgs]))
(def comcast {:name "Comcast"
              :path "https://www.todo.com"
              :title "Software Engineer"
              :details ["Designed and implemented new API endpoints and enhanced existing services built with the typelevel Http4s framework."
                        "Performed code optimizations and bug fixes in domains such as subscription management logic"
                        "Led the upgrade initiative of several internal and external dependencies such as cats and scala3 for project stability and performance."
                        "Configured CI/CD pipelines and updated configuration settings across multiple services to align with build scripts and ensure consistent deployment processes."
                        "Strengthened error handling mechanisms and updated test cases to ensure robustness in services."]
              :logo svgs/comcast-logo})

(def signalpath {:name "SignalPath"
                 :path "https://www.todo.com"
                 :title "Software Engineer"
                 :details ["Wrote and maintained Scala core platform services for a clinical trial management app's restful api."
                           "Partnered with UI engineers and fellow platform engineers in the process of defining and extending a backend api."
                           "Implemented passive, Kinisis pipeline based backend service functionality to keep distinct microservice data in sync with complex user business rules respected."
                           "Spearheaded new project to support higher-level facade api to wrap existing api, based on AWS Lambda."
                           "Performed system administration and performance troubleshooting in development and production environments."]
                 :logo svgs/signalpath-logo})

(def icidigital {:name "ici-Digital"
                 :path "https://www.todo.com"
                 :title "Java Developer"
                 :details ["Developed, implemented, and configured web content management solutions using a Java based CMS: AEM (Adobe CQ)."
                           "Translated business requirements into technical details relevant to AEM."
                           "Designed reusable AEM components for authoring content."
                           "Provided input into estimating engagement activities and execute engagements following the Agile methodology including SCRUM."
                           "Implemented mobile `hamburger` menu functionality based upon client business requirements in the form of a jQuery plugin."]
                 :logo svgs/ici-logo})

(def sciquest {:name "SciQuest"
               :path "https://www.todo.com"
               :title "Software Engineer"
               :details ["Extended and introduced new functionality to a large proprietary Java based web application tailored for Epro customers."
                         "Collaborated closely with architecture and senior developers in an Agile team environment to update business functionality based upon new customer needs."
                         "Worked with many Java based web technologies including JSP custom tags and Lucene search."]
               :logo svgs/sciquest-logo})

(def cargosphere {:name "Cargosphere"
                  :path "https://www.todo.com"
                  :title "Software Engineer"
                  :details ["Wrote new front end features for an `Excel on the web` user experience based on an existing Javascript spreadsheet library."
                            "Provided guidance and introductory help for incoming interns and new employees."]
                  :logo svgs/cargosphere-logo})

(def hcl {:name "HCL America "
          :path "https://www.todo.com"
          :title "Software Engineer"
          :details ["Worked under senior developer guidance to fulfill feature requests from business analysts."
                    "Updated several existing Java based web applications that comprised a larger system (an unemployment insurance system) at a full-stack capacity."]
          :logo svgs/hcl-logo})

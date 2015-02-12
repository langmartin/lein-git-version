(ns leiningen.git-version
  (:require [leiningen.help]
            [leiningen.jar]
            [leiningen.compile]
            [leiningen.core.main]
            [leiningen.core.project]
            [robert.hooke]
            [leiningen.test])
  (:use
   [clojure.java.shell :only [sh]]))

(defn- trimv
  [version]
  (if (and (string? version)
           (not-empty version)
           (= \v (first version)))
    (subs version 1)
    version))

(defn get-git-version
  ([] (trimv (get-git-version "--always" "--tags" "--dirty=-DIRTY")))
  ([& cmd]
   (-> (apply sh "git" "describe" cmd)
       (:out)
       (.trim)
       (not-empty))))

(defn get-git-branch
  []
  (-> (sh "git" "describe" "--contains" "--all" "HEAD")
      (:out)
      (.trim)))

(defn git-version
  "Show project version, as tagged in git."
  ^{:doc "Show git project version"}
  [project & args]
  (println (get-git-version)))

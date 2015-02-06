(ns lein-git-version.plugin
  (:use
   clojure.pprint
   leiningen.git-version))

(defn middleware
  [project]
  (-> project
      (assoc :version (get-git-version))
      (assoc :branch  (get-git-branch))))

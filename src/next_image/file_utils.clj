(ns next-image.file-utils
  (:require [clojure.java.io :as io]))

(defn get-files!
  [dir endswith]
  "Selects files in a dir ending with endswith. Removes files classified as hidden by java file io api"
  (->> dir
       io/file
       file-seq
       (filter #(.isFile %))
       (filter #(complement (.isHidden %)))
       (map #(.getAbsolutePath %))
       (filter #(.endsWith % endswith))
       sort))

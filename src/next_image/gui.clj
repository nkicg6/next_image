(ns next-image.gui
  (:require [seesaw.core :as seesaw]
            [seesaw.chooser :as chooser]
            [seesaw.font :as font]))

(def app-state (atom ""))

(defn get-files [e]
  (->> (chooser/choose-file :dir "~" :type :open :selection-mode :dirs-only)
       (reset! app-state)))

(def base-frame
  (seesaw/frame
   :id :base-frame
   :title "next_image v0.1-dev"
   :width 400 :height 400
   :content
   (seesaw/border-panel :center
                        (seesaw/label :text "<html>Welcome to <i>next image</i>!<br></br>Select the folder containing the images you are analyzing below to get started"
                :font "ARIAL-21"
                :h-text-position :center)
                        :south
                        (seesaw/button :text "Select your image folder"
                 :id :select-dirs
                 :listen [:action #(get-files %)])
                        :hgap 5 :vgap 10 :border 10)))

(-> base-frame seesaw/show!)

(defn rm []
  (seesaw/dispose! base-frame))

@app-state


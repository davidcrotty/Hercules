# Hercules

Create daily regimen so that you can see what you need to do today, and track how much you've done.

Base app on design from: (https://dribbble.com/shots/3719736-Bigger-biceps)
![alt text](https://cdn.dribbble.com/users/140092/screenshots/3719736/iphone_7.png "Original design")




# User Journeys

## Context
 User is at the gym. Likely warmed up and ready to go. A tablet will not likely be used in this scenario as it is bulky and harder to keep out of the way depending on the exercise or place into any gym equipments holder slots, so UI consistency  and visibility is key

### Scenario 1
User is exercising and wants to begin a regime.
Steps: 
1. User indicates that they would like to play a plan. This can either be from  a schedule  (see scenario 2) or ad hoc, as the user may not have had time to schedule a plan and wants to get straight to the core apps purpose. This may be being resumed or started again from a prior session.
2. Screen opens that allows them to interact to start a workout when they are ready. The current exercise for the set is displayed along with a count of the number of exercises needed. The next set is also displayed. If it is the end of that exercises set, the next type of exercise is shown.
3. User interacts to start workout.
4. Countdown begins so the user can prepare - this will need to be configurable. Alternatively they can skip this countdown.
5. Timer starts for the set - this will need to be configurable.
6. The UI indicates progress in the center as the timer ticks down, a notification/vibration is played when this ends. A small circle indicator counts down in a pie chart fashion on the current set. This will need to be pausable/resettable.  Depending on the UI size the number of exercises will need to have a method of viewing all available sets. 

> A user interview will be the best scenario to explore most common use cases as there are more possibilities, ie: skipping a set and selecting a set however this could overload the minimal flat UI and overload a novice user.
On completion of the subset the next set begins from step 5. The small circle indicator outlined in step 6 displays a tick to represent set completion.

7. If a break is added this will also be presented as a circle, this is common in workout videos to include a the break period for two reasons: UI consistency and motivation for the user to complete their task. (This is seen in similar apps such as https://play.google.com/store/apps/details?id=workout.homeworkouts.workouttrainer&hl=en , additionally when working out the user is able to process less information so bright visual indicators will be less cognitive overload on the user). See rules 1 and 8 of shneiderman's 8 golden rules of interface design.


### Considerations
Buttons need to be large and clear, when working out the user will likely put the phone to one side depending on the exercise. Minimal interaction should be required in this mode and configurations to the interaction made prior to being on this screen.

## Context
User not in an immediate exercise environment or with a personal trainer

### Scenario 2
User is planning their workout regime schedule, this will likely be pre workout or with a session with a personal trainer. They wish to add a predefined plan to their schedule.
Steps:
1. User is on the application dashboard, information of the day and access to different days are displayed.
2. User indicates they’d like to add a plan to that day.
3. User selects from list of predetermined plans. Multiple plans can be added to a day.
4. Another prompt for if this is ad hoc or a schedule.

### Scenario 3

User wants to create a plan which can consist of many exercises
Steps:
1. User is on dashboard, information of the day and access to different days are displayed.
2. User indicates they’d like to create a plan.
3. Prompt to enter the following details: plan name, and exercises included in plan. There is a prompt here to add an exercise if one is not found. Exercises are out of scope.
4. User confirms and is taken back to dashboard with feedback on plan creation.

# Designs
## Scenario 1
Adapted slightly to provide a play/pause button along with skip exercise. Allowing great flexibility and better user experience. Note this is not omitted as the tweaks are small, given the time constraints it was deemed unwise to waste time re-doing an already provided design.
## Scenario 2
![alt text](https://image.ibb.co/ieA3ZQ/dashboard_screen.png "Custom design")

Clicking the plus floating action button plays an animation which provides the options of Plan or exercise.

Clicking plan will produce a bottom sheet of current plans, with a highlighted option to create a new one. If an existing plan is tapped then a date view will appear, containing common preset dates and a custom option.

New plan icon will present scenario 3

## Scenario 3
![alt text](https://image.ibb.co/juYCTk/plan_screen.png "Custom design")

User inputs the name of their plan and adds subsequent exercises. UI allows quantity to be entered for flexibility as well as deletions by tapping the EDIT button.

Once completed a date picker is shown to schedule the plan as either ad-hoc or via a custom schedule UI with preset dates as well as a custom option.


# Installation and Usage
* Android Studio version used: `2.3.3`
* Kotlin version used: `1.1.4-2`
* Gradle plugin used: `2.3.3`
# Out of scope
Given the time constraints and other outstanding technical tests I have listed several features I would feel vital to UX that would be added if more time was available:

* Add exercise page.
* Account information / login.
* An app tutorial page, commonly found after ‘signing up’ or first app launch.
* Notifications to remind the user to exercise or of an existing plan/update to it.
* Sharing and social functions, ie a personal trainer adding exercises for a user.
* Voice recognition - very important UX
* Accessibility - very important for those hard of sight

# Assumptions
* In the designs the three green colours `#7DFC0A`, `#7FD219` and `#EAFD9F` do not meet WCAG 2.0 AA standards for accessibility when paired with the white and grey colours. This can be validated using http://webaim.org/resources/contrastchecker/. For this reason I have adapted the hex colours to pass AA standards as accessibility is a large part of UX which is one of the goals mentioned in the brief.

* Assumed OS version is Android 5.0 - 7.1.

* Add plan will not work, this is purely aesthetic only.

* Dates are not scrollable beyond yesterday, today and tomorrow. This is pulled from a mock data source as allowed in the brief.

# Devices Tested on
* Nexus 6P, OS 7.1
* Nexus 7 [2013], OS 6

# Known issues
* SVG for launch screen is not rendered correctly on Nexus 7.


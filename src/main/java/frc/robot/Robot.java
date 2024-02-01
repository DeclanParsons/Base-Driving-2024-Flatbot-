package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;


/**
 * This is a mutation of the differential drive demo code. 
 * This supports multiple spark max motor controllers 
 * Movement based on one analog input 
 */

public class Robot extends TimedRobot {

  private DifferentialDrive m_robotDrive;
  private Joystick m_leftStick; 
  //test


  

  @Override
  public void robotInit() {

    /* 
     * Init all motor controllers 
     */

    final PWMSparkMax right_motor = new PWMSparkMax(0);
    final PWMSparkMax top_right_motor = new PWMSparkMax(1);
    final PWMSparkMax left_motor = new PWMSparkMax(2);
    final PWMSparkMax top_left_motor = new PWMSparkMax(3);

    /* 
     * Add Child objects 
     */

    SendableRegistry.addChild(m_robotDrive, right_motor); 
    SendableRegistry.addChild(m_robotDrive, top_right_motor);
    SendableRegistry.addChild(m_robotDrive, left_motor);
    SendableRegistry.addChild(m_robotDrive, top_left_motor);

 
    /* 
     * Take child objects and link their movement to their "parent"
     */

    right_motor.addFollower(top_right_motor);  
    left_motor.addFollower(top_left_motor); 

    /* 
     * Invert left motor 
     */
    left_motor.setInverted(true);

    m_robotDrive = new DifferentialDrive(right_motor::set, left_motor::set);
    m_leftStick = new Joystick(1);
  }


  @Override
  public void teleopPeriodic() {

    /*
     * Affect motor output based on leftstick values 
     * (left controllers, right controllers)
     */

    m_robotDrive.tankDrive(m_leftStick.getY()+m_leftStick.getX(), m_leftStick.getY()-m_leftStick.getX());
  }
}
